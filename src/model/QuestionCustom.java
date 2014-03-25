package model;

import database.BaseSetting;
import exceptions.DecodeException;
import exceptions.EncodeException;
import interfaces.iDbManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QuestionCustom<SolutionType> extends Question implements iDbManager {

    // ----- ATTRIBUTES -----
    
    // Inherited

    /**
     * Solution of the question.
     */
    private SolutionType[] solution;

    // ----------------------
    
    // ---- CONSTRUCTORS ----
    
    /**
     * This constructor creates an empty question.
     */
    public QuestionCustom() {
        super();
        this.text = "Answer.";
        this.difficulty = 0;
        this.solution = null;
    }

    /**
     * This constructor creates a question,
     * with the text given in parameter.
     */
    public QuestionCustom(String QCtext) {
        super();
        if (QCtext != null) {
            this.text = QCtext;
        } else {
            this.text = "...";
        }
        this.difficulty = 0;
        this.solution = null;
    }
    
    /**
     * This constructor creates a question,
     * with the text and the solution given in parameters.
     */
    public QuestionCustom(String QCtext, SolutionType[] QCsolution) {
        super();
        if (QCtext != null) {
            this.text = QCtext;
        } else {
            this.text = "...";
        }
        this.difficulty = 0;
        this.solution = QCsolution;
    }

    /**
     * This constructor creates a question,
     * with the text and the difficulty given in parameters.
     */
    public QuestionCustom(String QCtext, int QCdifficulty) {
        super();
        if (QCtext != null) {
            this.text = QCtext;
        } else {
            this.text = "...";
        }
        if (QCdifficulty >= 0) {
            this.difficulty = QCdifficulty;
        } else {
            this.difficulty = 0;
        }
        this.solution = null;
    }
    
    /**
     * This constructor creates a question,
     * with the text, the difficulty and the solution given in parameters.
     */
    public QuestionCustom(String QCtext, int QCdifficulty, SolutionType[] QCsolution) {
        super();
        if (QCtext != null) {
            this.text = QCtext;
        } else {
            this.text = "...";
        }
        if (QCdifficulty >= 0) {
            this.difficulty = QCdifficulty;
        } else {
            this.difficulty = 0;
        }
        this.solution = QCsolution;
    }
    
    
    
    
    /*@Override
    public String encode() throws EncodeException() {
        StringBuilder res = new StringBuilder();
        return "";
    }*/

    // ----------------------
    
    // ------- METHODS ------
    
    // Inherited

    public void setSolution(SolutionType[] solution) {
        this.solution = solution;
    }
    
    public String encodeSolution() throws EncodeException {
        StringBuilder res = new StringBuilder();
        if (this.solution != null && this.solution.length > 0) {
            for (Object value : this.solution) {
                if (value instanceof Integer) {
                    res.append("int:");
                } else if (value instanceof Double) {
                    res.append("dbl:");
                } else if (value instanceof Character) {
                    res.append("chr:");
                } else if (value instanceof String) {
                    res.append("str:");
                } else if (value instanceof Boolean) {
                    res.append("bln:");
                } else if (value == null) {
                    res.append("nul:");
                } else {
                    throw new EncodeException("Unsupported variable type");
                }
            }
            res.replace(res.length()-1, res.length(), "");
            res.append("><");
            for (Object value : this.solution) {
                res.append(value).append(':');
            }
            res.replace(res.length()-1, res.length(), "");
        } else {
            throw new EncodeException("Empty solution array");
        }
        return res.toString();
    }
    
    public static QuestionCustom decodeSolution() throws DecodeException {
        QuestionCustom res =null;
        
        return res;
    }
    
    public SolutionType[] solve() {
        return solution;
    }
    
    /**
     * Display a question custom.
     */
    public String toString() {
        String res = "		QuestionCustom";
        res = res + "\n                 Text: " + this.text;
        res = res + "\n                 Difficulty: " + this.difficulty;
        res = res + "\n                 Solution: " + this.solution;
        return res;
    }

    // ----------------------
    
    // ----- DB METHODS -----

    /* MISE A JOURS */
    public boolean insert(BaseSetting bs) 
    {
        Connection connection = bs.getConnection();
        
        try
        {
            String query = "INSERT INTO QuestionCustom (text_qe, diff_qe , solutions_qe) VALUES (?,?,?)";
            PreparedStatement p_statement = connection.prepareStatement(query);
            p_statement.setString(1, this.text);
            p_statement.setInt(2, this.difficulty);
            //p_statement.setString(3, this.encodeSolutions());
            ResultSet rs = p_statement.getGeneratedKeys();
            
            if (rs.next()) this.id = rs.getInt(1);
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
        }
        
        return false;
    }

    public boolean update(BaseSetting bs) 
    {
        Connection connection = bs.getConnection();
        
        try
        {
            if (this.id < 0)
            {
                String query = "UPDATE QuestionCustom SET (text_qcustom = ? , diff_qcustom = ? , solutions_qcustom = ?) WHERE id_qcustom = ?";
                PreparedStatement p_statement = connection.prepareStatement(query);
                p_statement.setString(1, this.text);
                p_statement.setInt(2, this.difficulty);
                //p_statement.setText(3, this.encodeSolutions());
                p_statement.setInt(4, this.id);
                p_statement.executeUpdate();
            }
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
        }
        
        return false;
    }

    public boolean delete(BaseSetting bs) 
    {
        Connection connection = bs.getConnection();
        
        try
        {
            if (QuestionCustom.findById(id, bs) != null)
            {
                String query = "DELETE FROM QuestionCustom WHERE id_qcustom = ?";
                PreparedStatement p_statement = connection.prepareStatement(query);
                p_statement.setInt(1,id);
                p_statement.executeUpdate();
            }
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
        }
        
        return false;
    }

    /* FINDERS */
    public static QuestionCustom findById(int id, BaseSetting bs) 
    {
        return null;
    }

    // ----------------------

    @Override
    public ArrayList<Question> findAll_ByIdExercise(int ide, BaseSetting bs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
