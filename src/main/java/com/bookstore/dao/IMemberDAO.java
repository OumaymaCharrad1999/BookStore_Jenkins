package com.bookstore.dao;

import java.util.List;
import com.bookstore.entity.Member;

public interface IMemberDAO {
	
	List<Member> getMembers();
	Member getMember(int bookId);
	Member createMember(Member member);
	Member updateMember(int memberId,Member member);
	boolean deleteMember(int memberId);

}
