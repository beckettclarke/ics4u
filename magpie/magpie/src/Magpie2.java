public class Magpie2
{
    /**
     * Pick a default response to use if nothing else fits.
     * @return a non-committal string
     */
    private String getRandomResponse()        
    {
        final int NUMBER_OF_RESPONSES = 9;
        int whichResponse = (int) (Math.random() * NUMBER_OF_RESPONSES);      
        String response = "";

        if (whichResponse == 0)
        {
            response = "Interesting.";
        }
        else if (whichResponse == 1)
        {
            response = "Okay.";
        }
        else if (whichResponse == 2)
        {
            response = "I haven't heard that before.";
        }
        else if (whichResponse == 3)
        {
            response = "What else could you tell me about that?";
        }else if (whichResponse == 4)
        {
            response = "That's so cool!";
        }
        else if (whichResponse == 5)
        {
            response = "Tell me more!";
        }
        else if (whichResponse == 6)
        {
            response = "Wow!";
        }
        else if (whichResponse == 7)
        {
            response = "Fascinating";
        }
        else if (whichResponse == 8)
        {
            response = "How do you feel about that?";
        }
        return response;
    }

     /**
     * Get a default greeting 	
     * @return a greeting
     */
    public String getGreeting()
    {
        return "Hello, let's talk. Type in \"Bye\" to end our chat.";
    }

    /**
     * Gives a response to a user statement
     * 
     * @param statement the user statement
     * @return a response based on the rules given
     */
    public String getResponse(String statement)
    {
        String response = "";        
        if (statement.contains("math") || statement.contains("science")){
          response = "Is that your favorite class?";
        } else if (statement.contains("Crescent")){
          response = "Crescent School is a school commited to building men of character from boys of promise. Using the four core values of Respect, Responsibilty, Honesty and Compassion, students at Crescent are taught with character-infused lessons, and educated on what it truly means to be a man of character";
        } else if (statement.contains("Temu")){
          response = "Temu is an excellent online e-commerce platform that has recently gained a lot of popularity.";
        } else {
          response = getRandomResponse();    
        }   
        return response;
    }
}