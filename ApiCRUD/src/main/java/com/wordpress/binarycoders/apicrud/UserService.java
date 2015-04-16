package com.wordpress.binarycoders.apicrud;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

@Stateless
public class UserService {
    
    @PersistenceContext
    private EntityManager em;
    
    public void create(@NotNull User user) {
        this.em.persist(user);
    }
    
    public User findById(@NotNull Long id) {
        return this.em.find(User.class, id);
    }
    
    public List<User> findAll() {
        TypedQuery<User> typedQuery = this.em.createNamedQuery(User.FIND_ALL, User.class);

        return typedQuery.getResultList();
    }
    
    public void update(@NotNull User user) {
        this.em.merge(user);
    }
    
    public void delete(@NotNull Long id) {
        this.em.remove(this.findById(id));
    }
}
