package com.socio.security;
import static org.springframework.ldap.query.LdapQueryBuilder.query;

import java.util.List;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.stereotype.Repository;
@Repository
public class LdapUserRepoImpl implements LdapUserRepo {
   private LdapTemplate ldapTemplate;
   @Autowired
   public void setLdapTemplate(LdapTemplate ldapTemplate) {
      this.ldapTemplate = ldapTemplate;
   }

   @Override
public List<String> getAllLdapUsers() {
      return ldapTemplate.search(
         query().where("objectclass").is("person"),
         new AttributesMapper<String>() {
            public String mapFromAttributes(Attributes attrs)
               throws NamingException {
               return attrs.get("uid").get().toString();
            }
         });
   }
}