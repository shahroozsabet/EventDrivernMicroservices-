package com.microservices.demo.analytics.service.transformer;

import com.microservices.demo.analytics.service.dataaccess.entity.AnalyticsEntity;
import com.microservices.demo.kafka.avro.model.TwitterAnalyticsAvroModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.IdGenerator;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AvroToDbEntityModelTransformer {

    private final IdGenerator idGenerator;

    public List<AnalyticsEntity> getEntityModel(List<TwitterAnalyticsAvroModel> avroModels) {
        return avroModels.stream()
                .map(avroModel -> new AnalyticsEntity(
                        idGenerator.generateId()
                        , avroModel.getWord()
                        , avroModel.getWordCount()
                        , LocalDateTime.ofInstant(Instant.ofEpochMilli(avroModel.getCreatedAt()), ZoneOffset.UTC)))
                .toList();
    }
}
