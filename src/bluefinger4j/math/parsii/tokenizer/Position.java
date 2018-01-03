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

/**
 * Describes a position in a file or a stream based on lines and the character position within the line.
 */
@SuppressWarnings("squid:S1214")
public interface Position {

    /**
     * Represents an unknown position for warnings and errors which cannot be associated with a defined position.
     */
    Position UNKNOWN = new Position() {

        @Override
        public int getLine() {
            return 0;
        }

        @Override
        public int getPos() {
            return 0;
        }
    };

    /**
     * Returns the line number of this position.
     *
     * @return the one-based line number of this position
     */
    int getLine();

    /**
     * Returns the character position within the line of this position
     *
     * @return the one-based character position of this
     */
    int getPos();

}
