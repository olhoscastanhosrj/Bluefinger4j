/*
* The MIT License (MIT)
* 
* Copyright (c) 2013 scireum GmbH
*
* Permission is hereby granted, free of charge, to any person obtaining a copy of
* this software and associated documentation files (the "Software"), to deal in
* the Software without restriction, including without limitation the rights to
* use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
* the Software, and to permit persons to whom the Software is furnished to do so,
* subject to the following conditions:
*
* The above copyright notice and this permission notice shall be included in all
* copies or substantial portions of the Software.
*
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
* IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
* FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
* COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
* IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
* CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/

package bluefinger4j.math.parsii.tokenizer;

import java.util.List;

/**
 * Used to signal that processing an input failed.
 * <p>
 * By first collecting as many {@link ParseError} instances as possible, this permits to provide good insights
 * of what is wrong with the input provided by the user.
 */
public class ParseException extends Exception {

    private static final long serialVersionUID = -5618855459424320517L;

    private final transient List<ParseError> errors;

    private ParseException(String message, List<ParseError> errors) {
        super(message);
        this.errors = errors;
    }

    /**
     * Creates a new exception based on the list of errors.
     *
     * @param errors the errors which occurred while processing the user input
     * @return a new ParseException which can be thrown
     */
    public static ParseException create(List<ParseError> errors) {
        if (errors.size() == 1) {
            return new ParseException(errors.get(0).getMessage(), errors);
        } else if (errors.size() > 1) {
            return new ParseException(String.format("%d errors occured. First: %s",
                                                    errors.size(),
                                                    errors.get(0).getMessage()), errors);
        } else {
            return new ParseException("An unknown error occured", errors);
        }
    }

    /**
     * Provides a list of all errors and warnings which occurred
     *
     * @return all errors and warnings which occurred while processing the user input
     */
    public List<ParseError> getErrors() {
        return errors;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (ParseError error : errors) {
            if (sb.length() > 0) {
                sb.append("\n");
            }
            sb.append(error);
        }

        return sb.toString();
    }
}
