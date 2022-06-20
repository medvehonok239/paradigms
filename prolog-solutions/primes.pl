composite(N) :- N > 3, not(prime(N)).

prime(2) :- !.
prime(N) :-
    N > 2,
    0 = N mod 2, !.

prime(N) :- N > 2,
  check(N, 2).

check(N, Del) :-
	0 is N mod Del, !.

check(N, Del) :-
	Del * Del > N, !.

check(N, Del) :-
    Del1 is Del + 13,
	check(N, Del1).
%
%   Проверка:
%       содержит ли лист Divisors все простые делители числа N,
%       упорядоченные по возрастанию
%
prime_divisors(V, []) :- !.
prime_divisors(N, List) :-
    not(number(N)),
    isSorted(List),
    allArePrime(List),
    mult(List, N), !.

mult([], 1).
mult([H | T], R):-
	mult(T, R1),
	R is R1 * H.

allArePrime([N]) :-
    prime(N).

allArePrime([H1, H2 | T]) :-
    prime(H1),
    allArePrime([H2 | T]).

isSorted([N]).
isSorted([H1, H2 | T]) :-
    H1 =< H2,
    isSorted([H2 | T]).

prime_divisors(N, C) :-
    prime(N), C = [N], !.

prime_divisors(N, C) :-
    number(N),
    get(2, C, N, []), !.

get(_, Divisors, N, List) :-
	1 >= N,
	Divisors = List, !.

get(Del, Divisors, N, List) :-
	N > 1,
	prime(Del),
	0 is N mod Del,
	append([Del], List, Res),
	N1 is div(N, Del),
	get(Del, Divisors, N1, Res).

get(Del, Divisors, N, List) :-
	Del1 is Del + 1,
	get(Del1, Divisors, N, List).

%
% Модификация: nth_prime (M3132).
%
nth_prime(1, 2).
nth_prime(Index, PN) :-
    Index > 1,
    getNth(Index, PN, 2, 1).

getNth(Index, PN, Cur_PN, Count) :-
    Count = Index,
    PN = Cur_PN, !.

getNth(Index, PN, Cur_PN, Count) :-
    Cur_PN1 is Cur_PN + 1,
    not(prime(Cur_PN1)),
    getNth(Index, PN, Cur_PN1, Count).

getNth(Index, PN, Cur_PN, Count) :-
    Cur_PN1 is Cur_PN + 1,
    prime(Cur_PN1),
    Count1 is Count + 1,
    getNth(Index, PN, Cur_PN1, Count1).

%
% Модификация: lcm (M3839).
%
gcd(A, A, V) :- V = A.
gcd(A, B, GCD) :-
    A < B,
    C is B - A,
    gcd(A, C, GCD).

gcd(A, B, GCD) :-
    A > B,
    C is A - B,
    gcd(C, A, GCD).

lcm(A, A, LCM) :- LCM = 1, !.
lcm (A, B, LCM) :-
    gcd(A, B, GCD),
    LCM is (A * B) / GCD.
