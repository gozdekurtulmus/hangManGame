

package HangManProject;
import java.util.Scanner;

public class hangMan {

    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        System.out.println("Hi!What's your name?");
        String name=input.nextLine();
        System.out.println("Nice to meet you "+name+"! Let's play.");
        // wordList array is to choose a word randomly from it.Other two is for randomly chosen answers.
        String[] wordList={"age","angel","army","sworn","atomic","black","blade","blue","burn","chaos","plasmic","claw","oblivion","turquoise","draconic","dragon","neon","fallen","shadow","flame","fire","wing","galaxy","hades","light"};
        String[] trueAnswer={"Hmm.Okay,nice.","Pft.Yeah,true.","Aah!Good job.","Unfortunately its true.","Its true,pft!","Okay.Well done.","Its true.","THAT'S WRO- Oh.Its true.Pft.","OK!TRUE.","Yup.True."};
        String[] wrongAnswer={"HAHA!You're wrong","It's wrong.","You're wrong.","Your guess is false.","Thank you.Your wrong guess made me happy.","Are you even trying? Its false.","It looks like you want to kill the stickman.","Good job!Your guess is wrong.","Well done!It's wrong.Haha.","It's not even close lol."};

        int gamer=0; // gamer integer looks if the player wants to play again.
        while(gamer==0){
            //First choosing a random word from the array.
            int choose=(int)(Math.random()*25);
            String chosen=wordList[choose];
            System.out.println("I chose your word.Now try to find it before the stickman dies.");
            int counter=chosen.length()*2; // This is how many chances the player will have.
            char[] knowThis= new char[chosen.length()]; // knowThis array makes word hidden.

            for(int i=0;i<chosen.length();i++){
                 knowThis[i]='-';}
        
            System.out.print(knowThis);
            System.out.println();
            //control array is to check if the player find all the letters and compares with knowthis array.
            char[] lastControl= new char[chosen.length()];
            for(int h=0;h<chosen.length();h++){ lastControl[h]=chosen.charAt(h);
           }

            while(counter>0){ // Looks if the player have chances left.
                System.out.println("You have "+counter+" chances left.Enter a letter or press 0 for guessing all word.");
                String key=input.next(); //Takes the letter guess from the user.
                if(Character.isDigit(key.charAt(0))){ //Looks if the input is digit. It means player wants to guess all word.

                    if(Integer.parseInt(key)==0){ // If the player wants to know all word,this section starts working.
                         System.out.println("So,what do you think it is?");
                         String guess=input.next();
                         if(guess.equals(chosen)){ //If the guess is true,it congratulates and asks for another round.
                             System.out.println("OKAY.Okay.You won. Here, take your stickman.");
                             System.out.println("If you want to play again, press 0. If you don't want to see me again press -1.");
                             gamer=input.nextInt();
                             counter=-1;
                             continue;}

                         else if (!guess.equals(tostring(lastControl))) //If the guess is false, it tells the answer and asks for another round.
                         {
                             System.out.println("Thats sad... You lost "+name+ ". \nThe answer was "+chosen+".\nIf you want to play again just press 0. If you hate me press -1. \nI love you.");
                             gamer=input.nextInt();
                             counter=-1;
                             continue;
                         }
                    }
                }

                char pressed=key.charAt(0);
                if(whereIs(chosen,pressed)!=-1){ //If the input letter is found in the word, this section starts working.
                    int randAnswer=(int)(Math.random()*10);
                    System.out.println(trueAnswer[randAnswer]);
                }
                else if(whereIs(chosen,pressed)==-1){ //if the input letter is not in the word, this section starts working.
                    counter--;

                    if(counter==0){ //If the chances are finished.
                         System.out.println("Thats sad... You lost "+name+ ". \nThe answer was "+chosen+".\nIf you want to play again just press 0. If you hate me press -1. \nI love you.");
                         gamer=input.nextInt();
                         continue;
                    }
                    int randAnswer=(int)(Math.random()*10);
                    System.out.println(wrongAnswer[randAnswer]);
                 }
                 for(int i=0;i<chosen.length();i++){ // Changes the secret array with known letter.
                     if(whereIs(chosen,pressed)==i){
                         knowThis[i]=pressed;
                     }
                     if(whereIs2(chosen,pressed)==i){ // Looks if the array has the same letter two times and changes with the secret array.
                        if(whereIs2(chosen,pressed)==whereIs(chosen,pressed))
                            break;
                     knowThis[i]=pressed;
                     }
                 }
                 System.out.println(knowThis);
                 System.out.println();
                 if(tostring(knowThis).equals(tostring(lastControl))){ //If the player founds out all the letters, this section starts working.
                     System.out.println("OKAY.Okay.You won. Here, take your stickman.");
                     System.out.println("If you want to play again press 0. If you don't want to see me again press -1.");
                     gamer=input.nextInt();
                     counter=-1;
                 }
            }
        }
        System.out.println("Hah!I also dont like you. Bye.");


    }
      //Method to search for the letter if its in the word or not. And also control the answer.
        public static int whereIs(String word, char a){
             for (int i = 0; i < word.length(); i++){
                 if (a==(word.charAt(i)))
                 return i;
             }
             return -1;
    
        }

        public static int whereIs2(String word, char a){
            for (int i =word.length()-1; i>=0; i--){
                 if (a==(word.charAt(i)))
                    return i;
            }
            return -1;
        }

        public static String tostring( char[] array){
            StringBuilder control= new StringBuilder();
             for(int i=0;i<(array.length-1);i++)
                 control.append(array[i]);
             return control.toString();
        }
}