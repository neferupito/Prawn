package com.nefee.prawn.data.dao;

import com.nefee.prawn.data.entity.ArmorItem;
import com.nefee.prawn.data.model.Slot;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ArmorItemRepository extends CrudRepository<ArmorItem, Long> {

    List<ArmorItem> findBySlot(Slot slot);

}
