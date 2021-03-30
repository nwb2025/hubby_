package com.example.hubby.frameworks

import com.example.core.domain.models.HabitDomainLayer
import com.example.hubby.data.db.Habit
import com.example.hubby.utils.EntityMapper.EntityMapper

class HabitDBMapper : EntityMapper <Habit , HabitDomainLayer>
{
    override fun mapFromEntity(entity: Habit): HabitDomainLayer {
        return HabitDomainLayer(
            id = entity.id,
            count =  entity.count,
            date = entity.date,
            description = entity.description,
            frequency = entity.frequency,
            priority = entity.priority,
            name = entity.name,
            type = entity.type
        )
    }

    override fun mapToEntity(domainModel: HabitDomainLayer): Habit {
        return Habit(
            id = domainModel.id,
            count = domainModel?.count,
            date = domainModel.date,
            description = domainModel.description,
            frequency = domainModel.frequency,
            priority = domainModel.priority,
            name = domainModel.name,
            type = domainModel.type
            )
    }

    fun fromEntityList( entity : List <Habit> ) : List<HabitDomainLayer> {
        return entity.map{ mapFromEntity(it)}
    }


    fun mapToEntityList( entity : List <HabitDomainLayer> ) : List<Habit> {
        return entity.map{ mapToEntity(it)}
    }
}