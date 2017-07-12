package com.nefee.prawn.data.dao;

import com.nefee.prawn.data.entity.WowClass;
import com.nefee.prawn.data.entity.WowSpec;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WowSpecRepository extends CrudRepository<WowSpec, Long> {

    List<WowSpec> findByWowClass(WowClass wowClass);

    WowSpec findByWowId(Integer wowId);

}
