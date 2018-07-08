/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordbook;

/**
 *
 * @author jeff_jeong
 */
public class Vocabulary {
    private String word;
    private String partOfSpeech;
    private String meaning;
    private String example;
    
    //default 생성자
    public Vocabulary(){
        this.word = "";
        this.partOfSpeech = "";
        this.meaning = "";
        this.example = "";
    }
    //복사생성자
    public Vocabulary(Vocabulary source){
        this.word = source.word;
        this.partOfSpeech = source.partOfSpeech;
        this.meaning = source.meaning;
        this.example = source.example;
    }
    //매개변수를 갖는 생성자
    public Vocabulary(String word, String partOfSpeech, String meaning, String example)
    {
        this.word = word;
        this.partOfSpeech = partOfSpeech;
        this.meaning = meaning;
        this.example = example;
    }
    
    public boolean IsEqual(Vocabulary other)
    {
        boolean ret = false;
        if(this.word.compareTo(other.word)==0 && this.partOfSpeech.compareTo(other.partOfSpeech)==0
                && this.meaning.compareTo(other.meaning)==0 && this.example.compareTo(other.example)==0)
        {
            ret = true;
        }
        return ret;
    }
    
    public boolean IsNotEqual (Vocabulary other)
    {
        boolean ret = false;
        if(this.word.compareTo(other.word) != 0 || this.partOfSpeech.compareTo(other.partOfSpeech) != 0 
                || this.meaning.compareTo(other.meaning) != 0 || this.example.compareTo(other.example) != 0)
        {
            ret = true;
        }
        return ret;
    }
    
    public String GetWord()
    {
        return this.word;
    }
    
    public String GetPartOfSpeech()
    {
        return this.partOfSpeech;
    }
    
    public String GetMeaning()
    {
        return this.meaning;
    }
    
    public String GetExample()
    {
        return this.example;
    }
    
    public static void main(String[] args)
    {
        Vocabulary vocabulary = new Vocabulary("cellphone", "명사", "휴대전화", "Where is my cellphone?" );
        Vocabulary other = new Vocabulary("car", "명사", "자동차", "I have a car.");
        System.out.println(vocabulary.GetWord() + " " + vocabulary.GetPartOfSpeech() + " " 
                + vocabulary.GetMeaning() + " " + vocabulary.GetExample());
        if(vocabulary.IsNotEqual(other) == true)
        {
            System.out.println("다른 단어입니다.");
        }
    }
    
}
