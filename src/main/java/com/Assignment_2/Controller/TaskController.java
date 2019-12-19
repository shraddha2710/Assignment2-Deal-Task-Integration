package com.Assignment_2.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.Assignment_2.Model.DealResource;
import com.Assignment_2.Model.State;
import com.Assignment_2.Model.TaskResource;
import com.Assignment_2.Repository.DealRepository;
import com.Assignment_2.Repository.TaskRepository;
import com.Exception.DealNotFoundException;
import com.Exception.TaskNotFoundException;
import com.validation.Validation;

@RestController
@RequestMapping(path = "task")
public class TaskController extends DealResource{
	private static Logger logger = Logger.getLogger(TaskController.class);
	@GetMapping("/")
	public String testTask()
	{
		return "Task Controller";
	}
	
	public DealResource deal;
	
	@Autowired
	TaskRepository taskRepository;
	
	/*
	 * Api for create task and save in db
	 * required name validation
	 */
	@PostMapping("/create_task")
	public TaskResource create(@RequestBody TaskResource task) throws Exception {
		logger.info("Task create data :");
		String name = task.getName();
		Validation.ValidateRequiredField(name);
		TaskResource taskresource = taskRepository.save(task);
		logger.info("Task create data : " + " "+"Task Id: " +taskresource.getTaskId()+ " " + "Task Name : " + " "+taskresource.getName()
		+" "+"Deal Id" + " " + taskresource.getId());
		return taskresource;
	}
	
	/*
	 * Api for get all task
	 * pagination
	 * @Requestparam pageno ,@Requestparam pagesize
	 * return all task 
	 */
	@GetMapping("/get_task")
	public List<TaskResource> get(@RequestParam Integer pageNo, @RequestParam Integer pageSize) {
		
		Pageable page = PageRequest.of(pageNo, pageSize);
		Page<TaskResource> pg = taskRepository.findAll(page);
        if(pg.hasContent()) {
        	return pg.getContent();
        }
        
        else
        	return new ArrayList<TaskResource>();
       }
	
	
	/*
	 *  Api for update task
	 *  @Pathvariable taskid
	 *  @throws TaskNotFoundException
	 *  return updated task
	 */
	
	@PutMapping("/update_task/{id}")
	public TaskResource updateTask(@PathVariable(value = "id") Long taskId,@RequestBody TaskResource task) throws TaskNotFoundException {
		logger.info("Updated task data");
		TaskResource taskResource = taskRepository.findById(taskId).orElseThrow(()->new TaskNotFoundException(taskId));
		taskResource.setName(task.getName());
		taskResource.setStartOn(task.getStartOn());
		taskResource.setClosedOn(task.getClosedOn());
		taskResource.setDescription(task.getDescription());
		taskResource.setState(task.getState());
		taskResource.setCreatedBy(task.getCreatedBy());
		TaskResource updatetask = taskRepository.save(taskResource);
		logger.info("Updated task data : "+ " " +"Task Id:" + " "+updatetask.getTaskId()+ " "+"Task Name : " + " " 
		+updatetask.getName());
		return updatetask;
	}
	
	/*
	 * Api for delete task
	 * @PathVariable taskid
	 * @throws TaskNotFoundException
	 */
	
	@DeleteMapping("/remove_task/{id}")
	public ResponseEntity<?> removeTask(@PathVariable(value = "id") Long taskId ) throws TaskNotFoundException{
		TaskResource taskResource = taskRepository.findById(taskId).orElseThrow(() ->new TaskNotFoundException(taskId));
			taskRepository.delete(taskResource);
			logger.info("Deleted Task Data : " + " " + taskResource.getId());
			return ResponseEntity.ok().build();
	}
	
	/*
	 * Api to get particular task by id
	 * @Pathvariable taskid
	 * @throws TaskNotFoundException
	 * return task by id
	 */
	@GetMapping("/getById/{id}")
	public TaskResource getByid(@PathVariable(value="id") Long taskId) throws TaskNotFoundException{
		logger.info("Get task by Id" + " " + taskRepository.findById(taskId));
		return taskRepository.findById(taskId).orElseThrow(()->new TaskNotFoundException(taskId));
	}
	
	
	public List<TaskResource> getAlltask() throws TaskNotFoundException
	{
		List<TaskResource> task1 = new ArrayList<TaskResource>();
		task1 = (List<TaskResource>) taskRepository.findAll();
		return task1;
	}
	/* 
	 * Api to get task by name
	 * @throws TaskNotFoundException
	 * use of stream api to get particular task by name
	 * return tasks 
	 */
	@GetMapping("/getTaskByName")
	public List<TaskResource> getTask() throws TaskNotFoundException
	{
		List<TaskResource> task = getAlltask();
		List<TaskResource> task1 = task.stream().filter(t1 -> t1.getCreatedBy().equals("shraddha")).collect(Collectors.toList());
		for(TaskResource t : task1)
		{
			logger.info("Task by name :"+" "+"Task Id: " +t.getTaskId()+" " +"Task Name :" + " " + t.getName());
		}
		return task1;
	}
	
	/*
	 * Api to get task by sort
	 * @requestparam to get task by sort 
	 * return task list by sort
	 */
	@GetMapping("/get_TaskBySort")
	public List<TaskResource> get(@RequestParam String sortBy) {
		Sort sortOrder = Sort.by(sortBy);
		List<TaskResource> list = (List<TaskResource>) taskRepository.findAll(sortOrder);
		for(TaskResource t : list) {
			logger.info("Task by sort:" + " "+"Task Id: "+t.getId() + " " +"Task Name " +" "+t.getName() );
		}
		return list;
	 }

}
