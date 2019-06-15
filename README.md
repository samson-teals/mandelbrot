# Starter files for Mandelbrot

The `Main` class itself isn't supposed to be interesting: it only hosts a simple `Canvas` that we can draw on to.
Classes that implement the `IterableImage` interface can be used in the `Main` class.
The `Main` class then draws the image from `IterableImage` classes.

In fact, this `Main` class is the most simple of the ones we've worked with so far: it only hosts a timer, which triggers a `repaint` on every timer "tick".

Feel free to modify `Main`.
For example, you may want to change the main "demo" loop in the `public static void main` method.

# Mandelbrot Set

The Mandelbrot set isn't quite like the `IFS`s we looked at earlier, but it _is_ an iterating system.
The idea behind the set is that following expression can be iterated.
The set contains all the points on the complex plane that are unbounded for `n` for the following iterating expressions:

Z[0] = 0

Z[n+1] = Z[n]^2 + c

ABS(Z[n]) < 2

Where `Z` is a complex number, and `c` is a point on the complex plane.

Note that Z[n]^2 means to "square" the number.
Complex numbers square just like "normal" numbers: Z[n]^2 = Z[n] * Z[n].

"Unbounded" means that no matter how large `n` becomes, ABS(Z[n]) < 2.
Put another way, ABS(Z[n]) is "bounded" because it never gets to 2.

# Complex Numbers

Complex numbers are a system of numbers where sqrt(-1) is equal to the symbol `i`.
They are different from "normal" numbers in that they consist of a "real" part, and an "imaginary" part, represented by `i`.
This makes them a superset of normal numbers.
Otherwise, they behave as you would expect normal numbers (or polynomials) to behave.

The calculator at this site may be useful: https://www.symbolab.com/solver/complex-numbers-calculator.

## Adding

(A, B`i`) + (C, D`i`) = (A+C) + (B+D)`i`

e.g. (1, 2i) + (4, 8i) = (5, 10i)

This is also distributive so that:

A + B`i` + C + D`i` = (A+C) + (B+D)`i`

e.g. 1 + 2i + 4 + 8i = 1 + 4 + 2i + 8i = 5 + 10i = (5, 10i)

## Multiplication

Multiplying complex numbers is like multiplying polynomials, except that `i`^2, or `i`*`i`, is `-1`.

(A, B`i`) * (C, D`i`) = (A + B`i`) * (C + D`i`) = (A * C) + (A * D`i`) + (B`i` * C) + (B`i` * D`i`).

This re-arranges to:

(A * C) + (A * D`i`) + (C * B`i`) + (B`i` * D`i`) = (A * C) + ((AD + CB)`i`) + (B`i` * D`i`)

And reduces to:

(A * C) + ((AD + CB)`i`) + (B`i` * D`i`) = AC + (AD + CB)`i` + (-1 * BD)

e.g. (1, 2i) * (4, 8i) = (1 * 4) + (1*8 + 2*4)`i` + (-1 * 2*8) = 4 + 16`i` - 16 = (-12, 16`i`)

## ABS

The absolute value of the complex number is its distance to 0 on the complex plane.
You can use Pythagoras to calculate this number.

ABS(A, B`i`) = sqrt(A^2 + B^2)

e.g. ABS(1, 2i) = sqrt(1*1 + 2*2) = sqrt(5) = 2.236

# Implementing the Mandelbrot function

The Mandelbrot function can be implemented as a loop where the complex number `Z` is initially 0, and the next value of `Z` is calculated inside the loop.
Stop when the value of `Z` is greater than or equal to 2.

A `maxIterations` value should be used or else points that belong to the Mandelbrot set will take forever to calculate.
e.g. at `c` = (0, 0i), `Z[n]` is always 0, which is always less than 2.

# Coloring

Points that belong to the Mandelbrot set are traditionally colored black.
Points outside can be any color.
e.g. white is a good choice to start.

Another strategy is to add color.
Points that do not belong to the set take a variable number of iterations before they "escape" the bound of 2.
You can use that number to color those points.

In the `MandelbrotStarter.java` file, a `palette` array has been provided.
This can be used to provide a set of colors that correspond to the number of iterations a point takes to escape.
Since we know that the number of iterations is capped at `maxIterations`, the `palette` array only needs to have `maxIterations` + 1 elements.
Technically, the `+1` isn't necessary, but it takes litle room, and it makes thinking about the system easier.

# Optimization

You may notice that it takes a long time to calculate the Mandelbrot set.
You can make the set calculate faster if you know that the `abs` function is slow.
`Math.hypot` can be slow, and can be rewritten using `Math.sqrt`.
How can you rewrite the expression so that a `sqrt` is not necessary?

# Experiments

Try looking at different bounds.
For example, you can find other bounds here: http://www.cuug.ab.ca/dewara/mandelbrot/Mandelbrowser.html.

Most of the interesting bounds you'll find require a relatively large `maxIterations` value.
If you see something that doesn't look interesting, try setting `maxIterations` to 80+.