package com.Assignment_2.Controller;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Assignment_2.Model.DealResource;
import com.Assignment_2.Repository.DealRepository;
import com.Exception.DealNotFoundException;
import com.validation.Validation;

@RestController
@RequestMapping(path = "deal")
public class DealController {
	
	
	private static Logger logger = Logger.getLogger(DealController.class);
	@GetMapping("/")
	public String testdeal() {
		return " Deal Controller";
	}
	
	@Autowired
	DealRepository dealrepository;
	
	/*
	 * Api to create deal and save data in db
	 * amount cannot be neagative validation
	 */
	@PostMapping("/create_deal")
	public DealResource create(@RequestBody DealResource deal) throws Exception {
		logger.info("Deal Created: ");
		String dealname =  deal.getDealName();
		Double amt = deal.getAmount();
		Validation.ValidateRequiredField(dealname);
		Validation.validateAmount(amt);
		System.out.println("dealid" + deal.getId());
		DealResource dealresoucre = dealrepository.save(deal);
		logger.info("Deal Created Data: "+ "Deal Id :"+ " " + dealresoucre.getId() + " " + "Deal Name:" + dealresoucre.getDealName());
      return dealresoucre;
	}
	
	
	/*
	 * Api to get all deal
	 */
	@GetMapping("/get_deal")
	public List<DealResource> getAllDeal(){
		logger.info("Get All deal");
		List<DealResource> deal = dealrepository.findAll();
		for (DealResource d : deal) {
			logger.info("Deal Info :" + " "+"Deal Id : " +" "+ d.getId() + " " + "Deal Name:" + d.getDealName());
		}
	return dealrepository.findAll();
	
	}
	
	/*
	 * Api for update deal 
	 * @Pathvariable dealid
	 * @throws DealNotFoundException
	 * return updated deal
	 */
	@PutMapping("/update_deal/{id}")
	public DealResource update (@PathVariable(value ="id") Long dealid ,@RequestBody DealResource deal)throws Exception
	{
		logger.info("Updated deal data : ");
		 DealResource dealresource = dealrepository.findById(dealid).orElseThrow(()-> new DealNotFoundException(dealid));
		 String dealname =  deal.getDealName();
		 Double amt = deal.getAmount();

		 Validation.ValidateRequiredField(dealname);
		 dealresource.setDealName(deal.getDealName());
		 dealresource.setStartOn(deal.getStartOn());
		 dealresource.setClosedOn(deal.getClosedOn());
		 dealresource.setDescription(deal.getDescription());
		 dealresource.setCompany(deal.getCompany());
		 Validation.validateAmount(amt);
		 dealresource.setAmount(deal.getAmount());
		 
		 DealResource updateDeal = dealrepository.save(dealresource);
		 logger.info("Updated deal data" + " " + "Deal Id : " + updateDeal.getId() + " "+"Deal Name : "+ " " + updateDeal.getDealName());
		 return updateDeal;
		}
	
	/*
	 * Api for Delete deal 
	 * @pathvariable dealid
	 * @throws DealNotFoundException
	 * 
	 */
	@DeleteMapping("/remove_deal/{id}")
	public ResponseEntity<?> removeDeal(@PathVariable(value = "id") Long dealId) throws DealNotFoundException
	{	
		logger.info("Delete deal : ");
		DealResource dealResource = dealrepository.findById(dealId).orElseThrow(()->new DealNotFoundException(dealId));
		dealrepository.delete(dealResource);
		logger.info("Delete deal data : " +"Deal Id :"+" "+dealResource.getId());
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/getById/{id}")
	public DealResource getById(@PathVariable(value = "id") Long dealId) throws DealNotFoundException {
		DealResource deal = dealrepository.findById(dealId).orElseThrow(()-> new DealNotFoundException(dealId));
		logger.info("Deal data by id:"+"Deal Id :"+deal.getId()+" " +"Deal Name:"+" " + deal.getDealName() );
		return deal;
	}
	
}
