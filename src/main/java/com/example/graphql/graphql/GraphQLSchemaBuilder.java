package com.example.graphql.graphql;

import com.example.graphql.location.LocationDataFetchers;
import com.example.graphql.user.UserDataFetchers;
import com.merapar.graphql.schema.GraphQlSchemaBuilder;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;

@Slf4j
@Component
public class GraphQLSchemaBuilder implements GraphQlSchemaBuilder {

    @Resource
    private UserDataFetchers userDataFetchers;

    @Resource
    private LocationDataFetchers locationDataFetchers;

    @Getter
    private GraphQLSchema schema;

    @PostConstruct
    public void init() throws IOException {
        SchemaParser parser = new SchemaParser();
        SchemaGenerator generator = new SchemaGenerator();

        File schemaFile = new ClassPathResource("schema.graphqls").getFile();

        TypeDefinitionRegistry typeRegistry = parser.parse(schemaFile);
        RuntimeWiring wiring = buildRuntimeWiring();

        schema = generator.makeExecutableSchema(typeRegistry, wiring);
    }

    private RuntimeWiring buildRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("MutationType", typeWiring -> typeWiring
                        .dataFetcher("createUser", userDataFetchers.createUserDataFetcher()))
                .type("QueryType", typeWiring -> typeWiring
                        .dataFetcher("user", userDataFetchers.userDataFetcher())
                        .dataFetcher("location", locationDataFetchers.locationDataFetcher()))
                .type("User", typeWiring -> typeWiring
                        .dataFetcher("locations", userDataFetchers.locationDataFetcher()))
                .build();
    }

}
