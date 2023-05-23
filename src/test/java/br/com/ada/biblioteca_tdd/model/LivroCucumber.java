package br.com.ada.biblioteca_tdd.model;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/br/com/ada/biblioteca_tdd/resources/", 
                glue = {"br.com.ada.biblioteca_tdd.cucumber_glue"}, 
                tags = "@LivroCucumber",
                plugin = { "pretty", "html:cucumber-reports.html"})
public class LivroCucumber {
    
}
