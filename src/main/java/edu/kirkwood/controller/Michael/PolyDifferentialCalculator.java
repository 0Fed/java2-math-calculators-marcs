package edu.kirkwood.controller.Michael;

import edu.kirkwood.model.Michael.PolynomialTerm;
import edu.kirkwood.model.Michael.Polynomial;
import edu.kirkwood.view.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import static edu.kirkwood.view.Messages.*;
import static edu.kirkwood.view.UIUtility.pressEnterToContinue;
import static edu.kirkwood.view.UserInput.getString;

public class PolyDifferentialCalculator {
	
	public static void start()
	{
		Messages.PolyDiffHello();
		while(true) {
            String value = getString("Please enter the polynomial to be processed (or 'q' to quit)");
            if(value.equalsIgnoreCase("q") || value.equalsIgnoreCase("quit"))
            {
                break;
            }
            else
            {
            	ArrayList<PolynomialTerm> termList = isolateTerms(value);
            	if (termList == null)
            	{
            		continue;
            	}
            	Polynomial p = new Polynomial(termList);
            	Polynomial original = new Polynomial();
            	for (PolynomialTerm t : termList)
            	{
            		original.addTerm(t.clone());
            	}
            	p.differentiate();
            	System.out.println();
            	System.out.println("Thus, the derivative is: " + p);
            	System.out.println();
            }
		}
		Messages.PolyDiffFarewell();
	}
	/**
	 * The master parsing method; calls several helper methods to
	 * process user input.
	 * @param input a String provided by the user.
	 * @return ArrayList of PolynomialTerms compiled from the input string.
	 */
	private static ArrayList<PolynomialTerm> isolateTerms(String input)
	{
		ArrayList<String> intermediary = new ArrayList<String>();
		ArrayList<PolynomialTerm> result = new ArrayList<PolynomialTerm>();
		Pattern aTerm = Pattern.compile("([+-]?[^-+]+)");
    	Matcher matchTerms = aTerm.matcher(input);
    	while (!matchTerms.hitEnd())
    	{
    		if (matchTerms.find())
    		{
    			intermediary.add(matchTerms.group().trim());
    		}
    	}
//    	// Test code
//    	for (String s : intermediary)
//    	{
//    		System.out.println(s);
//    	}
    	
    	try
    	{
    		validate(intermediary);
	    	result = parseTerms(intermediary);
    	}
    	catch (IndexOutOfBoundsException e)
    	{
    		System.out.println(e.getMessage());
    		return null;
    	}
    	
    	return result;
	}
	/**
	 * This method will validate user input, and if an error is detected,
	 * will give the user a chance to either correct it or delete it.
	 * @param input The list of terms, in String form.
	 * @throws IndexOutOfBoundsException if the final remaining term is designated for removal.
	 */
	private static void validate(ArrayList<String> input) throws IndexOutOfBoundsException
	{
		Pattern shouldBe = Pattern.compile(
                    "((([+-]?)(\\s)*([0-9]*)([a-zA-Z])?)(\\^)?(?<=\\^)([0-9]?))|"   // Standard order 2+ term form
                +   "(([+-])(\\s)?([0-9]*)([a-zA-Z]))|"                             // Standard linear term form
                +   "(([+-])(\\s)?[0-9])"                                           // Standard constant term form
        );
		Matcher matcher = shouldBe.matcher("");
		for (int i = 0; i < input.size(); i++)
		{
			matcher.reset(input.get(i));
			if (!matcher.matches())
			{
				boolean reset = true;
				while (reset)
				{
					String newString = getString("There seems to be an issue with Term " + i +
							", \'" + input.get(i) + "\'.\nWould you like to re-enter it? (y/n)");
					if (newString.equalsIgnoreCase("y"))
					{
						System.out.println("Understood. Replacing Term " + i + ".");
						String newVal = getString("Please enter the new term: ");
						input.add(i, newVal);
						input.remove(i + 1);
						i--;
						reset = false;
					}
					else if (newString.equalsIgnoreCase("n"))
					{
						System.out.println("Understood. Dropping Term " + i + ".");
						input.remove(i);
						i--;
						if (input.size() == 0)
						{
							throw new IndexOutOfBoundsException("The only term was removed.");
						}
						break;
					}
					else
					{
						System.out.println("Unrecognized response received.");
					}
				}
				continue;
			}
		}
	}
	/**
	 * A helper method to translate Strings into PolynomialTerm objects.
	 * @param input A list of strings to be processed.
	 * @return The input list, converted to PolynomialTerms.
	 */
	private static ArrayList<PolynomialTerm> parseTerms(ArrayList<String> input)
	{
		ArrayList<PolynomialTerm> result = new ArrayList<PolynomialTerm>();
		Pattern coeff = Pattern.compile("[+-]?[\\s]?[0-9]+");
		Pattern var = Pattern.compile("[a-zA-Z]{1}");
		Pattern exp = Pattern.compile("(?<=\\^)([0-9]+)");
		Matcher match = exp.matcher("");
		for (String term : input)
		{
			int expInt = Integer.MIN_VALUE;
			int coeInt = Integer.MIN_VALUE;
			String varParsed = null;
			String temp = "";
			
			
			match.reset(term);
			match.usePattern(exp);
			if (match.find())
			{
				temp = match.group();
				temp = temp.replace(" ", "");
				temp = temp.replace("+", "");
				try
				{
					expInt = Integer.parseInt(temp);
				}
				catch (Exception e)
				{
					System.out.println("Should never see this.");
				}
				match.reset();
			}
			
			match.usePattern(var);
			if (match.find())
			{
				varParsed = match.group();
				match.reset();
			}
			
			match.usePattern(coeff);
			if (match.find())
			{
				temp = match.group();
				temp = temp.replaceAll(" ", "");
				temp = temp.replaceAll("[+]", "");
				try
				{
					coeInt = Integer.parseInt(temp);
				}
				catch (Exception e)
				{
					System.out.println("Should never see this 2.");
				}
			}
			
			result.add(buildTerm(coeInt, varParsed, expInt));			
		}
		return result;
	}
	/**
	 * Helper method for constructing PolynomialTerms from parameters.
	 * Uses the various PolynomialTerm constructors to ensure accuracy.
	 * @param c The coefficient of the new PolynomialTerm.
	 * @param v The variable of the new PolynomialTerm.
	 * @param e The exponent of the new PolynomialTerm.
	 * @return A PolynomialTerm built using the three parameters.
	 */
	private static PolynomialTerm buildTerm(int c, String v, int e)
	{
		PolynomialTerm result = null;
		if (v != null)
		{
			if (e > Integer.MIN_VALUE)
			{
				if (c > Integer.MIN_VALUE)
				{
					result = new PolynomialTerm(c, v, e);
				}
				else
				{
					result = new PolynomialTerm(v, e);
				}
			}
			else
			{
				if (c > Integer.MIN_VALUE)
				{
					result = new PolynomialTerm(c, v);
				}
				else
				{
					result = new PolynomialTerm(v);
				}
			}
		}
		else
		{
			result = new PolynomialTerm(c);
		}
		
		return result;
	}
	
//	Old split code, now unused. Method "splitTerms" replaced by "isolateTerms"
//	//	for ease of use.
	
//	private static ArrayList<String> splitTerms(String input)
//	{
//		String[] parts = input.split("(?=((\\s)(\\+|-)(\\s))|(?<=((\\s)(\\+|-)(\\s))))");
//		ArrayList<String> terms = new ArrayList<String>(Arrays.asList(parts));
//		return terms;
//	}
}
