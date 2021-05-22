package TypingApp;

public class SentenceSelector {

    private String[] paragraph = new String[] {
            "The quick fox jumped over the lazy dog. The quick fox jumped over the lazy dog. The quick fox jumped over the lazy dog. ",

            "Proofreader applicants are tested primarily on their spelling, speed, " +
                    "and skill in finding errors in the sample text. " +
                    "Toward that end, they may be given a list of ten or twenty classically" +
                    " difficult words and a proofreading test, both tightly timed. ",

            "The proofreading test will often have a maximum number of errors per " +
                    "quantity of text and a minimum amount of time to find them. " +
                    "The goal of this approach is to identify those with the best skill set."
    };

    public String getNextExample() {
        return paragraph[0];
    }
}

