package com.bookstore.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bookstore.entity.Member;
import com.bookstore.services.IMemberService;

@Controller
@RequestMapping("memberservice")
public class MemberController {
	
	@Autowired
	private IMemberService service;
	
	@GetMapping("members")
	public ResponseEntity<List<Member>> getMembers(){
		List<Member> members = service.getMembers();
		return new ResponseEntity<List<Member>>(members, HttpStatus.OK);
	}
	
	@GetMapping("members/{id}")
	public ResponseEntity<Member> getMember(@PathVariable("id") Integer id){
		Member member = service.getMember(id);
		return new ResponseEntity<Member>(member, HttpStatus.OK);
	}
	
	@PostMapping("members")
	public ResponseEntity<Member> createMember(@RequestBody Member member){
		Member m = service.createMember(member);
		return new ResponseEntity<Member>(m, HttpStatus.OK);	
	}
	
	@PutMapping("members/{id}")
	public ResponseEntity<Member> updateMember(@PathVariable("id") int id, @RequestBody Member member){
		Member m = service.updateMember(id, member);
		return new ResponseEntity<Member>(m, HttpStatus.OK);
	}

	@DeleteMapping("members/{id}")
	public ResponseEntity<String> deleteMember(@PathVariable("id") int id){
		boolean isDeleted = service.deleteMember(id);
		if(isDeleted){
			String responseContent = "Member has been deleted successfully";
			return new ResponseEntity<String>(responseContent,HttpStatus.OK);
		}
		String error = "Error while deleting member from database";
		return new ResponseEntity<String>(error,HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
