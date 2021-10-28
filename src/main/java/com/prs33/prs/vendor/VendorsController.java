package com.prs33.prs.vendor;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@CrossOrigin
@RestController
@RequestMapping("/api/vendors")

public class VendorsController {

		@Autowired 
		private VendorRepository vendorRepo;
		
		@GetMapping
		public ResponseEntity<Iterable<Vendor>> GetAll() {
			var vendors = vendorRepo.findAll();
			return new ResponseEntity<Iterable<Vendor>>(vendors, HttpStatus.OK);
		}
		
		@GetMapping("{id}")	
		public ResponseEntity<Vendor> GetById(@PathVariable int id) {
			var vendor = vendorRepo.findById(id);
			if(vendor.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Vendor>(vendor.get(), HttpStatus.OK);
		}
		
		@PostMapping
		public ResponseEntity<Vendor> Insert(@RequestBody Vendor vendor) {
			if(vendor == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			vendor.setId(0);
			var newVendor = vendorRepo.save(vendor);
			return new ResponseEntity<Vendor>(newVendor, HttpStatus.CREATED);
		}
		
		@SuppressWarnings("rawtypes")
		@PutMapping("{id}")
		public ResponseEntity Update(@PathVariable int id, @RequestBody Vendor vendor ) {
			if(vendor.getId() != id) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			var oldVendor = vendorRepo.findById(vendor.getId());
			if(oldVendor.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			vendorRepo.save(vendor);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		@SuppressWarnings("rawtypes")
		@DeleteMapping("{id}")
		public ResponseEntity Delete(@PathVariable int id) {
			var vendor = vendorRepo.findById(id);
			if (vendor.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			vendorRepo.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
}
