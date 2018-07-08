/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordbook;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


/**
 *
 * @author jeff_jeong
 */
public class WordBook {

    /**
     * @param args the command line arguments
     */
    
    
    private ArrayList<Vocabulary> vocabularies;
    private int capacity = 256;
    private int length;
    
    //매개변수를 갖는 생성자
    public WordBook(int capacity)
    {
        this.vocabularies = new ArrayList<Vocabulary>(capacity);
        this.capacity = capacity;
        this.length = 0;
    }
    
    //복사생성자
    public WordBook(WordBook source)
    {
        this.vocabularies = new ArrayList<Vocabulary>(source.vocabularies);
        this.capacity = source.capacity;
        this.length = source.length;
    }
    
    public int Record(String word, String partOfSpeech, String meaning, String example)
    {
        Vocabulary vocabulary = new Vocabulary(word, partOfSpeech, meaning, example);
        int index;
        
        if(this.length < this.capacity)
        {
            this.vocabularies.add(this.length, vocabulary);
        }
        else
        {
            this.vocabularies.add(vocabulary);
            this.capacity++;
        }
        index = this.vocabularies.lastIndexOf(vocabulary);
        this.length++;
        
        return index;
    }
    
    public void Find(String word, int[] indexes, int[] count)
    {
        int i = 0;
        int j = 0;
        count[0] = 0;
        Vocabulary vocabulary;
        while(i < this.length)
        {
            vocabulary = this.GetAt(i);
            if(vocabulary.GetWord().compareTo(word)==0)
            {
                indexes[j] = i;
                count[0] = count[0] + 1;
                j++;
            }
            i++;
        }
    }
    
    public int Correct(int index, String partOfSpeech, String meaning, String example)
    {
        Vocabulary vocabulary = new Vocabulary(this.vocabularies.get(index).GetWord(),partOfSpeech,meaning,example);
        this.vocabularies.set(index, vocabulary);
        return index;
    }
    
    public int Erase(int index)
    {
        this.vocabularies.remove(index);
        this.capacity--;
        this.length--;
        index = -1;
        
        return index;
    }
    
  
    public static Comparator<Vocabulary> WordComparator = new Comparator<Vocabulary>() 
    {
        @Override
        public int compare(Vocabulary one, Vocabulary other)
        {
            return one.GetWord().compareTo(other.GetWord());
        }
    };
    
    public void Arrange()
    {
        Collections.sort(this.vocabularies,WordComparator);
    }
    
      public Vocabulary GetAt(int index)
    {
        return this.vocabularies.get(index);
    }
    
    public int GetCapacity()
    {
        return this.capacity;
    }
    
    public int GetLength()
    {
        return this.length;
    }
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        WordBook wordBook = new WordBook(2);
        int index;
        int index1;
        
        index1 = wordBook.Record("apple", "명사", "사과", "Wow~ this apple looks so delicious!");
        Vocabulary vocabulary = wordBook.GetAt(index1);
        System.out.println(vocabulary.GetWord()+ " "+vocabulary.GetPartOfSpeech()+" "
                            +vocabulary.GetMeaning()+" "+vocabulary.GetExample());
        
        System.out.println(wordBook.GetLength());
        index = wordBook.Record("fly", "명사", "파리", "Oh, please catch that fly for me.");
        vocabulary = wordBook.GetAt(index);
        System.out.println(vocabulary.GetWord()+ " "+vocabulary.GetPartOfSpeech()+" "
                            +vocabulary.GetMeaning()+" "+vocabulary.GetExample());
        System.out.println(wordBook.GetLength());
        
        index = wordBook.Correct(index, "동사", "날다", "I feel like flying to the sky.");
        vocabulary = wordBook.GetAt(index);
        System.out.println(vocabulary.GetWord()+ " "+vocabulary.GetPartOfSpeech()+" "
                            +vocabulary.GetMeaning()+ " "+vocabulary.GetExample());
        
        index = wordBook.Record("pizza", "명사", "피자", "Pizza is one of my favorites");
        vocabulary = wordBook.GetAt(index);
        System.out.println(vocabulary.GetWord()+ " "+vocabulary.GetPartOfSpeech()+" "
                        +vocabulary.GetMeaning()+" "+vocabulary.GetExample());
        System.out.println(wordBook.GetLength());
        
        index = wordBook.Record("pizza", "명사", "피자", "I can eat a whole pizza.");
        vocabulary = wordBook.GetAt(index);
        System.out.println(vocabulary.GetWord()+ " "+vocabulary.GetPartOfSpeech()+" "
                        +vocabulary.GetMeaning()+" "+vocabulary.GetExample());
        System.out.println(wordBook.GetLength());
        
        index1 = wordBook.Erase(index1);
        
        
        if(index1 == -1)
        {
            System.out.println("잘 지워졌습니다.");
        }
        System.out.println(wordBook.GetLength());
        
        int[] indexes = new int [wordBook.GetLength()];
        int[] count = new int [1];
        wordBook.Find("pizza", indexes, count);
        
        index = 0;
        while (index < count[0])
        {
            vocabulary = wordBook.GetAt(indexes[index]);
            System.out.println(vocabulary.GetWord()+" "+vocabulary.GetPartOfSpeech()+" "+vocabulary.GetMeaning()+
                    " "+vocabulary.GetExample());
            index++;
        }
        
        index = 0;
        while(index < wordBook.GetLength())
        {
            vocabulary = wordBook.GetAt(index);
            System.out.println(vocabulary.GetWord()+" "+vocabulary.GetPartOfSpeech()+" "
                                +vocabulary.GetMeaning()+" "+vocabulary.GetExample());
            index++;
        }
        
    }
}

  