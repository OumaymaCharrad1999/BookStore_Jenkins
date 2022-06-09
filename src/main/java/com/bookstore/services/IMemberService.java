package com.bookstore.services;

import java.util.List;
import com.bookstore.entity.Member;

public interface IMemberService {
	
	List<Member> getMembers();
	Member getMember(int memberId);
	Member createMember(Member member);
	Member updateMember(int memberId, Member member);
	boolean deleteMember(int memberId);

}
