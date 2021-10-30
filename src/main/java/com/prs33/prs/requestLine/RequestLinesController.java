package com.prs33.prs.requestLine;

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
import org.springframework.web.bind.annotation.RestController;




@CrossOrigin
@RestController
@RequestMapping("/api/requestLines")
public class RequestLinesController {
	
	@Autowired
	private RequestLineRepository reqLinRepo;
	
	@GetMapping
	public ResponseEntity<Iterable<RequestLine>> GetAll() {
		var requestLines = reqLinRepo.findAll();
		return new ResponseEntity<Iterable<RequestLine>>(requestLines, HttpStatus.OK);
	}
	
	@GetMapping("{id}")	
	public ResponseEntity<RequestLine> GetById(@PathVariable int id) {
		var requestLine = reqLinRepo.findById(id);
		if(requestLine.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	return new ResponseEntity<RequestLine>(requestLine.get(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<RequestLine> Insert(@RequestBody RequestLine requestLine) {
		if(requestLine == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		requestLine.setId(0);
		var newRequestLine = reqLinRepo.save(requestLine);
		return new ResponseEntity<RequestLine>(newRequestLine, HttpStatus.CREATED);
	}
	
	@SuppressWarnings("rawtypes")
	@PutMapping("{id}")
	public ResponseEntity Update(@PathVariable int id, @RequestBody RequestLine requestLine) {
		if(requestLine.getId() != id) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		var oldRequestLine = reqLinRepo.findById(requestLine.getId());
		if(oldRequestLine.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		reqLinRepo.save(requestLine);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@SuppressWarnings("rawtypes")
	@DeleteMapping("{id}")
	public ResponseEntity Delete(@PathVariable int id) {
		var requestLine = reqLinRepo.findById(id);
		if (requestLine.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		reqLinRepo.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	

}
