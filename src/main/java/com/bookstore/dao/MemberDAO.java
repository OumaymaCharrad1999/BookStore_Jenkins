package com.bookstore.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bookstore.entity.Member;

@Transactional
@Repository
public class MemberDAO implements IMemberDAO {
	
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * This method is responsible to get all members available in database and return it as List<Member>
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Member> getMembers() {
		String hql = "FROM Member as atcl ORDER BY atcl.id";
		return (List<Member>) entityManager.createQuery(hql).getResultList();
	}

	/**
	 * This method is responsible to get a particular Book detail by given member id 
	 */
	@Override
	public Member getMember(int memberId) {
		return entityManager.find(Member.class, memberId);
	}

	/**
	 * This method is responsible to create new member in database
	 */
	@Override
	public Member createMember(Member member) {
		entityManager.persist(member);
		Member m = getLastInsertedMember();
		return m;
	}

	/**
	 * This method is responsible to update member detail in database
	 */
	@Override
	public Member updateMember(int memberId, Member member) {
		Member memberFromDB = getMember(memberId);
		memberFromDB.setCIN(member.getCIN());
		memberFromDB.setNom(member.getNom());
		memberFromDB.setPrenom(member.getPrenom());
		memberFromDB.setAdresse(member.getAdresse());
		entityManager.flush();
		Member updatedMember = getMember(memberId);
		return updatedMember;
	}

	/**
	 * This method is responsible for deleting a particular(which id will be passed that record) 
	 * record from the database
	 */
	@Override
	public boolean deleteMember(int memberId) {
		Member member = getMember(memberId);
		entityManager.remove(member);
		boolean status = entityManager.contains(member);
		if(status){
			return false;
		}
		return true;
	}
	
	/**
	 * This method will get the latest inserted record from the database and return the object of Member class
	 * @return member
	 */
	private Member getLastInsertedMember(){
		String hql = "from Member order by id DESC";
		Query query = entityManager.createQuery(hql);
		query.setMaxResults(1);
		Member member = (Member)query.getSingleResult();
		return member;
	}

}
