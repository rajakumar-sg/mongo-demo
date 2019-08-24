package com.example.mongo.demo.subtype.config;

import com.example.mongo.demo.subtype.model.InPersonContactInfo;
import com.example.mongo.demo.subtype.model.TelephoneContactInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.convert.TypeInformationMapper;
import org.springframework.data.mapping.Alias;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.util.ClassTypeInformation;
import org.springframework.data.util.TypeInformation;

import java.util.Arrays;

@Configuration
public class MongoConfig {

    @Autowired
    private MongoDbFactory mongoDbFactory;
    @Autowired
    private MongoMappingContext mongoMappingContext;

    @Bean
    public MappingMongoConverter mappingMongoConverter() {
        DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoDbFactory);
        MappingMongoConverter converter =
                new MappingMongoConverter(dbRefResolver, mongoMappingContext);


        TypeInformationMapper telephoneConverter = new TypeInformationMapper() {
            ClassTypeInformation<TelephoneContactInfo> TELEPHONE_CONTACT_TYPE = ClassTypeInformation.from(TelephoneContactInfo.class);

            @Override
            public TypeInformation<?> resolveTypeFrom(Alias alias) {
                if (alias.isPresent() && alias.getValue().equals("TELEPHONE")) {
                    return TELEPHONE_CONTACT_TYPE;
                }
                return null;
            }

            @Override
            public Alias createAliasFor(TypeInformation<?> type) {
                if (type.isAssignableFrom(TELEPHONE_CONTACT_TYPE)) {
                    return Alias.of("TELEPHONE");
                }
                return Alias.empty();
            }
        };

        TypeInformationMapper inpersonConverter = new TypeInformationMapper() {
            ClassTypeInformation<InPersonContactInfo> INPERSON_CONTACT_TYPE = ClassTypeInformation.from(InPersonContactInfo.class);

            @Override
            public TypeInformation<?> resolveTypeFrom(Alias alias) {
                if (alias.isPresent() && alias.getValue().equals("INPERSON")) {
                    return INPERSON_CONTACT_TYPE;
                }
                return null;
            }

            @Override
            public Alias createAliasFor(TypeInformation<?> type) {
                if (type.isAssignableFrom(INPERSON_CONTACT_TYPE)) {
                    return Alias.of("INPERSON");
                }

                return Alias.empty();
            }
        };

        DefaultMongoTypeMapper mongoTypeMapper =
                new DefaultMongoTypeMapper("contactMode",
                        Arrays.asList(telephoneConverter, inpersonConverter));

//        DefaultMongoTypeMapper mongoTypeMapper = new DefaultMongoTypeMapper(null);
        converter.setTypeMapper(mongoTypeMapper);
        return converter;
    }
}