package com.example.hubby.frameworks

import com.example.core.domain.models.HabitDomainLayer
import com.example.hubby.data.api.HabitResponse
import com.example.hubby.utils.EntityMapper.EntityMapper

class HabitRetrofitMapper : EntityMapper<HabitResponse, HabitDomainLayer> {
    override fun mapFromEntity(entity: HabitResponse): HabitDomainLayer {
        return HabitDomainLayer(
            id = 0,
            count =  entity.count,
            date = entity.date,
            description = entity.description,
            frequency = entity.frequency,
            priority = entity.priority,
            name = entity.title,
            type = entity.type,
            done_dates = entity.done_dates
        )
    }

    override fun mapToEntity(domainModel: HabitDomainLayer): HabitResponse
    {
        return HabitResponse(
            count =  domainModel.count,
            date = domainModel.date,
            description = domainModel.description,
            frequency = domainModel.frequency,
            priority = domainModel.priority,
            title = domainModel.name,
            type = domainModel.type,
            done_dates =  domainModel.done_dates
        )
    }

    fun fromEntityList( entity : List <HabitResponse> ) : List<HabitDomainLayer> {
        return entity.map{ mapFromEntity(it)}
    }

    fun mapToEntityList( entity : List <HabitDomainLayer> ) : List<HabitResponse> {
        return entity.map{ mapToEntity(it)}
    }
}