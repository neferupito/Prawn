package com.nefee.prawn.data.dao;

import com.nefee.prawn.data.entity.Relic;
import com.nefee.prawn.data.model.RelicType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RelicRepository extends CrudRepository<Relic, Long> {

    List<Relic> findByRelicType(RelicType relicType);

}
