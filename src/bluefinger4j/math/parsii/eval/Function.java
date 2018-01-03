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

package bluefinger4j.math.parsii.eval;

import java.util.List;

/**
 * Defines a function which can be referenced and evaluated from within expressions.
 */
public interface Function {

    /**
     * Returns the number of expected arguments.
     * <p>
     * If the function is called with a different number of arguments, an error will be created
     * <p>
     * In order to support functions with a variable number of arguments, a negative number can be returned.
     * This will essentially disable the check.
     *
     * @return the number of arguments expected by this function or a negative number to indicate that this
     * function accepts a variable number of arguments
     */
    int getNumberOfArguments();

    /**
     * Executes the function with the given arguments.
     * <p>
     * The arguments need to be evaluated first. This is not done externally to permit functions to perform lazy
     * evaluations.
     *
     * @param args the arguments for this function. The length of the given list will exactly match
     *             <tt>getNumberOfArguments</tt>
     * @return the result of the function evaluated with the given arguments
     */
    double eval(List<Expression> args);

    /**
     * A natural function returns the same output for the same input.
     * <p>
     * All classical mathematical functions are "natural". A function which reads user input is not natural, as
     * the function might return different results depending on the users input
     *
     * @return <tt>true</tt> if the function returns the same output for the same input, <tt>false</tt> otherwise
     */
    boolean isNaturalFunction();
}
