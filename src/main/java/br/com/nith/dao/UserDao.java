package br.com.nith.dao;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.nith.models.User;


@Repository
@Transactional
public class UserDao extends AbstractDao<Integer, User> {
	
	public User getByUsername(String username){
		return (User) getCriteria().add(Restrictions.eq("email", username)).uniqueResult();
	}
	
}
