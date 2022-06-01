package com.bookstore.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.dao.IMemberDAO;
import com.bookstore.entity.Member;

@Service
public class MemberService implements IMemberService {
	
	@Autowired
	private IMemberDAO dao;

	@Override
	public List<Member> getMembers() {
		return dao.getMembers();
	}
	
	@Override
	public Member getMember(int memberId) {
		return dao.getMember(memberId);
	}

	@Override
	public Member createMember(Member member) {
		return dao.createMember(member);
	}

	@Override
	public Member updateMember(int memberId, Member member) {
		return dao.updateMember(memberId,member);
	}

	@Override
	public boolean deleteMember(int memberId) {
		return dao.deleteMember(memberId);
	}

}
