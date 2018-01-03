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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

/**
 * An efficient reader of character streams, reading character by character and supporting lookaheads.
 * <p>
 * Helps to read characters from a {@link Reader} one after another. Using <tt>next</tt>, upcoming characters can
 * be inspected without consuming (removing) the current one.
 */
public class LookaheadReader extends Lookahead<Char> {

    private Reader input;
    private int line = 1;
    private int pos = 0;

    /**
     * Creates a new LookaheadReader for the given Reader.
     * <p>
     * Internally a {@link BufferedReader} is used to efficiently read single characters. The given reader will not
     * be closed by this class.
     *
     * @param input the reader to draw the input from
     */
    public LookaheadReader(Reader input) {
        if (input == null) {
            throw new IllegalArgumentException("input must not be null");
        }
        this.input = new BufferedReader(input);
    }

    @Override
    protected Char endOfInput() {
        return new Char('\0', line, pos);
    }

    @Override
    protected Char fetch() {
        try {
            int character = input.read();
            if (character == -1) {
                return null;
            }
            if (character == '\n') {
                line++;
                pos = 0;
            }
            pos++;
            return new Char((char) character, line, pos);
        } catch (IOException e) {
            problemCollector.add(ParseError.error(new Char('\0', line, pos), e.getMessage()));
            return null;
        }
    }

    @Override
    public String toString() {
        if (itemBuffer.isEmpty()) {
            return line + ":" + pos + ": Buffer empty";
        }
        if (itemBuffer.size() < 2) {
            return line + ":" + pos + ": " + current();
        }
        return line + ":" + pos + ": " + current() + ", " + next();
    }
}
