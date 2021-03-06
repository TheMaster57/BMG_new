package tests;

import database.BaseSetting;
import exceptions.DecodeException;
import model.QuestionCustom;

public class QuestionCustom_Tests 
{
    public static void main(String[] args)
    {
        BaseSetting bs = new BaseSetting();
        
        QuestionCustom qcustom1 = new QuestionCustom("Je suis la 1ere question personnalisee au monde! J'ai droit a la medaille d'or! <3", 0);
        QuestionCustom qcustom2 = new QuestionCustom("Je suis la 2nde question personnalisee au monde! J'ai droit a la medaille d'argent! :D", 0);
        QuestionCustom qcustom3 = new QuestionCustom("Je suis la 3eme question personnalisee au monde! J'ai droit a la medaille de bronze! :)", 0);
        QuestionCustom qcustom4 = new QuestionCustom("Je suis la 4eme question personnalisee au monde! Mais j'ai perdu de peu! :/", 0);
        QuestionCustom qcustom5 = new QuestionCustom("Je suis la 5eme question personnalisee au monde! Mais je suis dernier! :(", 0);
        
        try 
        {
            Object[] tab_qcustom1 = qcustom1.decodeSolution("dbl><2.72:3.14");
            Object[] tab_qcustom2 = qcustom1.decodeSolution("int><18:25");
            Object[] tab_qcustom3 = qcustom1.decodeSolution("str><\"azerty\":\"qwerty\"");
            Object[] tab_qcustom4 = qcustom1.decodeSolution("chr><\'a\':\'z\'");
            Object[] tab_qcustom5 = qcustom1.decodeSolution("str><\"exp\":\"pi\"");
            
            qcustom1.setSolution(tab_qcustom1);
            qcustom2.setSolution(tab_qcustom2);
            qcustom3.setSolution(tab_qcustom3);
            qcustom4.setSolution(tab_qcustom4);
            qcustom5.setSolution(tab_qcustom5);
        } 
        catch (DecodeException de) 
        {
            System.out.println("DECODE EXCEPTION");
        }
        
        System.out.println("\n"+"Insert - x5"+"\n");
        
        qcustom1.insert(bs);
        qcustom2.insert(bs);
        qcustom3.insert(bs);
        qcustom4.insert(bs);
        qcustom5.insert(bs);
        
        QuestionCustom qcustom;
        
        System.out.println("\n"+"FindById - no1"+"\n");
        
        qcustom = null;
        // ATTENTION AU FINDBYID (avant = static | maintenant != static)
        qcustom = QuestionCustom.findById(4, bs);
        System.out.println(qcustom);
        qcustom.setText("Eh mais j'ai droit au lot de consolation! et je le veux tout de suite!");
        
        System.out.println("\n"+"Update"+"\n");
        
        qcustom.update(bs);
        
        System.out.println("\n"+"FindById - no2"+"\n");
        
        qcustom = null;
        // ATTENTION AU FINDBYID (avant = static | maintenant != static)
        qcustom = QuestionCustom.findById(5, bs);
        System.out.println(qcustom);
        qcustom.setText("\"- Toi t'es dernier donc tu t'en vas! Au revoir!\"");
        
        System.out.println("\n"+"Delete"+"\n");
        
        qcustom.delete(bs);
        
        System.out.println("\n"+"FindAll"+"\n");
        
        // ATTENTION AU FINDALL (avant = static | maintenant != static)
        QuestionCustom[] tab_qcustom = QuestionCustom.findAll(bs);
        
        for (QuestionCustom qcust : tab_qcustom) 
        {
            System.out.println(qcust);
        }
    }
}
