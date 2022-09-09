package com.business.finance.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.business.finance.model.customer.CustomerInfoEntity;
import com.business.finance.request.FinanceRequest;
import com.business.finance.response.MemberInfoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.business.finance.repository.CustomerRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class FinanceAppController {
//
//	@Autowired
//	CustomerRepository tutorialRepository;
//
//
//	/**
//	 * This gives all finance entries irrective of business type
//	 * @param title
//	 * @return
//	 */
//	@GetMapping("/finance")
//	public FinanceRequest getAllFinance(@RequestParam(required = false) String title) {
//		try {
//			return null;
//		} catch (Exception e) {
//			return null;
//		}
//	}
//
//	@GetMapping("/finance/{businessType}")
//	public ResponseEntity<CustomerInfoEntity> getFinanceDataForBusiness(@PathVariable("businessType") String buisnessType) {
////		Optional<Tutorial> tutorialData = tutorialRepository.findById(id);
//
////		if (tutorialData.isPresent()) {
////			return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
////		} else {
////			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
////		}
//		return null;
//	}
//
//	@PostMapping("/finance")
//	public MemberInfoResponse addfinance(@RequestBody FinanceRequest financeRequest) {
//		try {
////			Tutorial _tutorial = tutorialRepository
////					.save(new Tutorial(tutorial.getTitle(), tutorial.getDescription(), false));
//			return null;
//		} catch (Exception e) {
//			return null;
////			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
//
//	@GetMapping("/finance/{userId}")
//	public ResponseEntity<CustomerInfoEntity> getFinanceDataForUser(@PathVariable("userId") String userId) {
////		Optional<Tutorial> tutorialData = tutorialRepository.findById(id);
//
////		if (tutorialData.isPresent()) {
////			return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
////		} else {
////			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
////		}
//		return null;
//	}
//
//	@GetMapping("/tutorials")
//	public ResponseEntity<List<CustomerInfoEntity>> getAllTutorials(@RequestParam(required = false) String title) {
//		try {
//			List<CustomerInfoEntity> tutorials = new ArrayList<CustomerInfoEntity>();
//
//			if (title == null)
//				tutorialRepository.findAll().forEach(tutorials::add);
//			else
//				tutorialRepository.findByTitleContaining(title).forEach(tutorials::add);
//
//			if (tutorials.isEmpty()) {
//				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//			}
//
//			return new ResponseEntity<>(tutorials, HttpStatus.OK);
//		} catch (Exception e) {
//			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
//
//
//	@GetMapping("/tutorials/{id}")
//	public ResponseEntity<CustomerInfoEntity> getTutorialById(@PathVariable("id") long id) {
//		Optional<CustomerInfoEntity> tutorialData = tutorialRepository.findById(id);
//
//		if (tutorialData.isPresent()) {
//			return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
//		} else {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//	}
//
//	@PostMapping("/tutorials")
//	public ResponseEntity<CustomerInfoEntity> createTutorial(@RequestBody CustomerInfoEntity tutorial) {
//		try {
//			CustomerInfoEntity _tutorial = tutorialRepository
//					.save(new CustomerInfoEntity(tutorial.getTitle(), tutorial.getDescription(), false));
//			return new ResponseEntity<>(_tutorial, HttpStatus.CREATED);
//		} catch (Exception e) {
//			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
//
//	@PutMapping("/tutorials/{id}")
//	public ResponseEntity<CustomerInfoEntity> updateTutorial(@PathVariable("id") long id, @RequestBody CustomerInfoEntity tutorial) {
//		Optional<CustomerInfoEntity> tutorialData = tutorialRepository.findById(id);
//
//		if (tutorialData.isPresent()) {
//			CustomerInfoEntity _tutorial = tutorialData.get();
//			_tutorial.setTitle(tutorial.getTitle());
//			_tutorial.setDescription(tutorial.getDescription());
//			_tutorial.setPublished(tutorial.isPublished());
//			return new ResponseEntity<>(tutorialRepository.save(_tutorial), HttpStatus.OK);
//		} else {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//	}
//
//	@DeleteMapping("/tutorials/{id}")
//	public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
//		try {
//			tutorialRepository.deleteById(id);
//			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		} catch (Exception e) {
//			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
//
//	@DeleteMapping("/tutorials")
//	public ResponseEntity<HttpStatus> deleteAllTutorials() {
//		try {
//			tutorialRepository.deleteAll();
//			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		} catch (Exception e) {
//			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//
//	}
//
//	@GetMapping("/tutorials/published")
//	public ResponseEntity<List<CustomerInfoEntity>> findByPublished() {
//		try {
//			List<CustomerInfoEntity> tutorials = tutorialRepository.findByPublished(true);
//
//			if (tutorials.isEmpty()) {
//				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//			}
//			return new ResponseEntity<>(tutorials, HttpStatus.OK);
//		} catch (Exception e) {
//			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}

}
