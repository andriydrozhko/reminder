package com.reminder.config;

import ma.glasnost.orika.Converter;
import ma.glasnost.orika.Mapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.TypeBuilder;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class MapperConfiguration extends ConfigurableMapper implements ApplicationContextAware {

    private MapperFactory factory;

    @Override
    protected void configure(MapperFactory factory) {
        super.configure(factory);
        this.factory = factory;
    }

    @Override
    protected void configureFactoryBuilder(DefaultMapperFactory.Builder factoryBuilder) {
        factoryBuilder.mapNulls(false);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        registerBeans(applicationContext);
    }

    private void registerBeans(ApplicationContext applicationContext) {
        applicationContext.getBeansOfType(Mapper.class).values().forEach(this::addMapper);
        applicationContext.getBeansOfType(Converter.class).values().forEach(this::addConverter);
    }

    private void addConverter(Converter converter) {
        factory.getConverterFactory().registerConverter(converter);
    }

    private void addMapper(Mapper<?, ?> mapper) {
        registerFromAToB(mapper);
        registerFromAListToB(mapper);
    }

    private void registerFromAToB(Mapper<?, ?> mapper) {
        factory.classMap(mapper.getAType(), mapper.getBType()).customize((Mapper) mapper).byDefault().register();
    }

    private void registerFromAListToB(Mapper<?, ?> mapper) {
        factory.classMap(new TypeBuilder<List<?>>() {}.build(), mapper.getBType()).customize((Mapper) mapper).register();
    }

}