(defn abstract-expr [f]
      (fn [arg1 arg2]
          (fn [val]
              (f (double (arg1 val)) (double (arg2 val))))))

(defn constant [n]
      (fn [_]
          (identity n)))

(defn variable [n]
      (fn [values]
          (get values n)))

(def add (abstract-expr +))
(def subtract (abstract-expr -))
(def multiply (abstract-expr *))

(defn div [a b]
      (/ (double a) (double b)))

(def divide (abstract-expr div))

(defn negate [n]
      (fn [val]
          (- (n val))))

(defn exp [n]
      (fn [val]
          (Math/exp (n val))))

(defn ln [n]
      (fn [val]
          (Math/log (Math/abs ^double (n val)))))

(def operations {
                 '+      add
                 '-      subtract
                 '*      multiply
                 '/      divide
                 'negate negate
                 'exp    exp
                 'ln     ln
                 })

(defn parseFunction [string]
      ((defn parse [c]
             (cond
               (number? c) (constant c)
               (list? c)
                     (apply (get operations (first c)) (mapv (fn [i] (parse i)) (rest c)))
               (symbol? c) (variable (str c)))
             ) (read-string string)))
