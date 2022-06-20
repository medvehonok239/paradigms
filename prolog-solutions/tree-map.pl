%
%   Тк изначально список отсортирован -> у нас дерево будет иметь вид:
%
%		    (K_1, V_1)
%					   \
%						 (K_2, V_2)
%									  \
%										(K_3, V_3)
%													\
%														. . .
%																 \
%																  	(K_n, V_n)
%
%	То есть будет всегда отсутствовать правый (левый) ребенок
%
map_build([], V) :- V = 0, !.
map_build(ListMap, TreeMap) :-
	build_tree(ListMap, TreeMap, tree(nil, nil, nil)).

build_tree([], TreeMap, Tree) :-
	TreeMap = Tree, !.

build_tree([(K, V) | T], TreeMap, CurrentTree) :-
	Q = tree(K, V, CurrentTree),
	build_tree(T, TreeMap, Q).

map_get(TreeMap, Key, Value):-
	tree_get(TreeMap, Key, Value).

tree_get(tree(Key, V, Parent), Key, Value) :-
	Value = V, !.

tree_get(tree(K, V, Parent), Key, Value) :-
	tree_get(Parent, Key, Value).

%
% Модификация: Replace(M3132)
%
contains(tree(K, _, _), K).
contains([], L) :- !.
contains(tree(Key, Value, Parent), K) :-
   contains(Parent, K).

map_replace(Map, Key, Value, Result) :-
	contains(Map, Key),
	tree_replace(Map, Key, Value, Result), !.

map_replace(Map, Key, Value, Result) :-
	Result = Map, !.

tree_replace(P, Key, Value, ResultTree) :-
    (P = tree(nil, nil, nil); P = 0), !,
	ResultTree = 0, !.

tree_replace(tree(Key, _, Parent), Key, Value, ResultTree) :-
	ResultTree = tree(Key, Value, Parent), !.

tree_replace(tree(K, V, Parent), Key, Value, ResultTree) :-
	tree_replace(Parent, Key, Value, Q),
	ResultTree = tree(K, V, Q), !.