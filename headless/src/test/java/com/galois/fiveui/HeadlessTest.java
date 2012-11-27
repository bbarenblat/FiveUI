/**
 * 
 */
package com.galois.fiveui;

import java.io.IOException;

import junit.framework.Assert;

import org.junit.Test;

import com.galois.fiveui.HeadlessAtom;
import com.galois.fiveui.HeadlessRunDescription;
import com.galois.fiveui.RuleSet;
import com.galois.fiveui.Utils;
import com.google.common.collect.ImmutableList;

/**
 * @author bjones
 *
 */
public class HeadlessTest {
	private static final String RUN_DESCRIPTION_DIR = "src/test/resources/runDescriptions/";

    /**
     * Test method for {@link com.galois.com.galois.fiveui.HeadlessRunDescription}, parses
     * 'src/test/resources/runDescriptions/headlessSample0.json'.
     * 
     * @throws IOException 
     */
    @Test
    public final void testDeserialize_headlessSample0() throws IOException {
        
        String jsonFileName = RUN_DESCRIPTION_DIR + "headlessSample0.json";
        String ruleSetLoc = 
                RUN_DESCRIPTION_DIR + "../ruleSets/emptyRuleSet.json";
        String ruleContent = Utils.readFile(ruleSetLoc);
        RuleSet ruleSetOracle = RuleSet.parse(ruleContent);
        HeadlessAtom headlessAtomOracle = 
                new HeadlessAtom("http://testhost", ruleSetOracle);  
        HeadlessRunDescription oracle = 
                new HeadlessRunDescription(ImmutableList.of(headlessAtomOracle));     
        
        HeadlessRunDescription actual = HeadlessRunDescription.parse(jsonFileName);
        assertObjEqual("Object deserialized incorrectly.", oracle, actual);
    }

    /**
     * Test method for {@link com.galois.com.galois.fiveui.HeadlessRunDescription}, parses
     * 'src/test/resources/runDescriptions/headlessSample1.json'.
     * 
     * @throws IOException 
     */
    @Test
    public final void testDeserialize_headlessSample1() throws IOException {
        
        String jsonFileName = RUN_DESCRIPTION_DIR + "headlessSample1.json";
        String ruleSetLoc = 
                RUN_DESCRIPTION_DIR + "../ruleSets/headingGuidelines.json";
        RuleSet ruleSetOracle = RuleSet.parse(Utils.readFile(ruleSetLoc));
        HeadlessAtom headlessAtomOracle = 
                new HeadlessAtom("http://testhost", ruleSetOracle);  
        HeadlessRunDescription oracle = 
                new HeadlessRunDescription(ImmutableList.of(headlessAtomOracle));     
        
        HeadlessRunDescription actual = HeadlessRunDescription.parse(jsonFileName);
        assertObjEqual("Object deserialized incorrectly.", oracle, actual);
    }
    
    /**
     * Test method for {@link com.galois.com.galois.fiveui.HeadlessRunDescription}, parses
     * 'src/test/resources/runDescriptions/headlessSample2.json'.
     * 
     * @throws IOException 
     */
    @Test
    public final void testDeserialize_headlessSample2() throws IOException {
        
        String jsonFileName = RUN_DESCRIPTION_DIR + "headlessSample2.json";
        // manually build first HeadlessAtom
        String ruleSetLoc1 = 
                RUN_DESCRIPTION_DIR + "../ruleSets/emptyRuleSet.json";
        RuleSet ruleSetOracle1 = RuleSet.parse(Utils.readFile(ruleSetLoc1));
        HeadlessAtom headlessAtomOracle1 = 
                new HeadlessAtom("http://testhost1", ruleSetOracle1);  
        // manually build second HeadlessAtom
        String ruleSetLoc2 = 
                RUN_DESCRIPTION_DIR + "../ruleSets/headingGuidelines.json";
        RuleSet ruleSetOracle2 = RuleSet.parse(Utils.readFile(ruleSetLoc2));
        HeadlessAtom headlessAtomOracle2 = 
                new HeadlessAtom("http://testhost2", ruleSetOracle2);  
        
        HeadlessRunDescription oracle = 
                new HeadlessRunDescription(ImmutableList.of(headlessAtomOracle1, 
                		                                    headlessAtomOracle2));     
        
        HeadlessRunDescription actual = HeadlessRunDescription.parse(jsonFileName);
        assertObjEqual("Object deserialized incorrectly.", oracle, actual);
    }
    
    private void assertObjEqual(String msg, Object oracle, Object actual) {
        Assert.assertTrue(msg + ";\n expected: "+oracle+"\n actual: "+actual,
                oracle.equals(actual));
    }
}
