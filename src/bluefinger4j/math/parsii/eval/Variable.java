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

/**
 * Represents a variable which binds a value to a name.
 * <p>
 * A variable is resolved or created using a {@link Scope}. This ensures that the same name always resolves to the
 * same variable. In contrast to using a Map, reading and writing a variable can be much faster, as it only needs
 * to be resolved once. Reading and writing it, is basically as cheap as a field access.
 * <p>
 * A variable can be made constant, which will fail all further attempts to change it.
 */
public class Variable {

    private double value = 0d;
    private String name;
    private boolean constant = false;

    /**
     * Creates a new variable.
     * <p>
     * Variables should only be created by their surrounding {@link Scope} so that all following look-ups
     * yield the same variable.
     *
     * @param name the name of the variable
     */
    protected Variable(String name) {
        this.name = name;
    }

    /**
     * Sets the value if the variable.
     *
     * @param value the new value of the variable
     * @throws IllegalStateException if the variable is constant
     */
    public void setValue(double value) {
        if (constant) {
            throw new IllegalStateException(String.format("%s is constant!", name));
        }
        this.value = value;
    }

    /**
     * Sets the given value and marks it as constant.
     *
     * @param value the new (and final) value of this variable
     */
    public void makeConstant(double value) {
        setValue(value);
        this.constant = true;
    }

    /**
     * Returns the value previously set.
     *
     * @return the value previously set or 0 if the variable is not written yet
     */
    public double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return name + ": " + value;
    }

    /**
     * Returns the name of the variable.
     *
     * @return the name of this variable
     */
    public String getName() {
        return name;
    }

    /**
     * Determines if this variable is constant.
     *
     * @return <tt>true</tt> if this variable cannot be modified anymore, <tt>false</tt> otherwise
     */
    public boolean isConstant() {
        return constant;
    }

    /**
     * Sets the value and returns <tt>this</tt>.
     *
     * @param value the new value of this variable
     * @return <tt>this</tt> for fluent method calls
     */
    public Variable withValue(double value) {
        setValue(value);
        return this;
    }
}
