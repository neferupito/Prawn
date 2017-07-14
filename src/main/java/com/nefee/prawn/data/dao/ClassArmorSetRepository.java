package com.nefee.prawn.data.dao;

import com.nefee.prawn.data.entity.ClassArmorSet;
import com.nefee.prawn.data.entity.WowClass;
import org.springframework.data.repository.CrudRepository;

public interface ClassArmorSetRepository extends CrudRepository<ClassArmorSet, Long> {

    ClassArmorSet findByWowClass(WowClass wowClass);

}
