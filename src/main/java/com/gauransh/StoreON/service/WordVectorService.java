package com.gauransh.StoreON.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import com.gauransh.StoreON.entity.ProductDetails;
import com.gauransh.StoreON.repository.SearchRepository;
import jakarta.annotation.PostConstruct;

@Service
public class WordVectorService {

    private final Map<String, double[]> wordVectors = new HashMap<>();
    
    private final int VECTOR_SIZE = 300;

    private final SearchRepository searchRepository;

    public WordVectorService(SearchRepository searchRepository) {		// using the words of search repository
        this.searchRepository = searchRepository;
    }

    @PostConstruct
    public void loadWordVectors() throws IOException {
    	
    	// this funciton gets all the unique words from teh data base
        Set<String> productWords = fetchUniqueWordsFromProductNames();	//declared the funtion below
        																// to fetchunique names
        File file = new ClassPathResource("models/glove.6B.300d.txt").getFile();  	// to load the dimentions
        
        try (BufferedReader br = new BufferedReader(new FileReader(file))) { //this ds gets teh dimentions
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(" ");			// this makes a character array with the value stored in dimentions
                String word = tokens[0];
                if (!productWords.contains(word)) continue;		// this is going to skip any word absent in 
                												//the glove for dimentions
                double[] vector = new double[VECTOR_SIZE];
                for (int i = 1; i <= VECTOR_SIZE; i++) {		// this is now helpin to traverse through
                												// each value of 300 dimentions
                    vector[i - 1] = Double.parseDouble(tokens[i]);	// and this charater array stores the 
                    												// the value in it ;
                }
                wordVectors.put(word, vector);		// this is making the vector with the 300 dimentions 
                									// in the HashMap (word and the vector with teh 300 dimention value)
            }
        }
        System.out.println("Loaded vectors for " + wordVectors.size() + " product-relevant words.");
    }

    

    private Set<String> fetchUniqueWordsFromProductNames() {
        List<ProductDetails> allProducts = searchRepository.findAll();		// getting all the products in the db
        Set<String> words = new HashSet<>();	// creating a HashsSet 
        for (ProductDetails product : allProducts) {		//for loop thorugh each product name
            String[] tokens = product.getName().toLowerCase().split("\\s+");	//creating a string with all lowercase name
            words.addAll(Arrays.asList(tokens));	// adding these tokens to strings to teh hashset
        }
        return words;
    }

    
    public List<String> getSimilarWords(String query) {
        final String normalizedQuery = query.toLowerCase(); //  made to lower case to avoid  confusion

        if (!wordVectors.containsKey(normalizedQuery)) return List.of();	// if word not there avoid it

        
        // now this is granting me with the value of the vectors to get similarity and stuff
        double[] queryVector = wordVectors.get(normalizedQuery);	

        
        
        return wordVectors.entrySet().stream()			// this si a pipeline of hashset
            .filter(e -> !e.getKey().equals(normalizedQuery)) // i don't want to compare the same word again so avoid that
            
            	//  wordVectors is a Map<String, double[]> — key is a word, value is its vector.					
            
            .sorted((e1, e2) -> Double.compare(			
                cosineSimilarity(queryVector, e2.getValue()),		
                cosineSimilarity(queryVector, e1.getValue())		
            ))
            .limit(5)		// to keep only the top 5 matches		
            .map(Map.Entry::getKey)		
            .collect(Collectors.toList());				// now geting the 5 most similar words
    }



    private double cosineSimilarity(double[] a, double[] b) {
        double dot = 0.0, normA = 0.0, normB = 0.0;
        for (int i = 0; i < VECTOR_SIZE; i++) {
            dot += a[i] * b[i];		// dot product
            normA += a[i] * a[i];	// to find magnitude
            normB += b[i] * b[i];	// to find magnitude
        }
        return dot / (Math.sqrt(normA) * Math.sqrt(normB)); //cos(theta)= dot product / unit vectors 
    
    }
}
