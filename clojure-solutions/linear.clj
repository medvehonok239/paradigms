
"Chapter Vectors :"

(defn v+ [v1 v2]
      "Returns sum of two vectors."

      (mapv + v1 v2))

(defn v- [v1 v2]
      "Returns subtract of two vectors."

      (mapv - v1 v2))

(defn v* [v1 v2]
      "Returns multiply of two vectors."

      (mapv * v1 v2))

(defn vd [v1 v2]
      "Returns divide of two vectors."

      (mapv / v1 v2))

(defn v*s [v s]
      "Returns multiply of vector and number.

       NOTE:
       vector = [] -> []
       vector === 0 -> 0."

      (cond
        (= 0.0 (last v)) v
        (empty? v) []
        (int? s) (mapv #(* % s) v)
        ))

(defn scalar [v1 v2]
      "Returns scalar multiply of 2 vectors."

      (reduce + (v* v1 v2)))

(defn vect [v1 v2]
      "Returns vector multiply of 2 vectors."

      ((fn [v1 v2]
           [(- (* (v1 1) (v2 2)) (* (v1 2) (v2 1)))
            (- (* (v1 2) (v2 0)) (* (v1 0) (v2 2)))
            (- (* (v1 0) (v2 1)) (* (v1 1) (v2 0)))]
           ) v1 v2))


"Chapter Matrices :"

(defn m+ [m1 m2]
      "Returns sum of two matrix."

      (mapv v+ m1 m2))

(defn m- [m1 m2]
      "Returns subtract of two matrix."

      (mapv v- m1 m2))

(defn m* [m1 m2]
      "Returns multiply of two matrix."

      (mapv v* m1 m2))

(defn md [m1 m2]
      "Returns divide of two matrix."

      (mapv vd m1 m2))

(defn m*s [m s]
      "Returns multiply of matrix and number.

       NOTE:
       matrix = [] -> []
       matrix === 0 -> 0."
      (mapv #(v*s % s) m))

(defn m*v [m1 y]
      "Returns multiply of matrix and vector."

      (partial
        (mapv (fn [v11]
                  (scalar v11 y)) m1)))

(defn transpose [coll]
      "Returns transpose matrix."

      (partial (apply mapv vector coll)))

(defn m*m [a b]
      "Returns matrix multiply."

      (partial
        (mapv
          (fn [m] (m*v (transpose b) m)) a)))

"Modification: M3132 (Cuboid)."

(defn c+ [a b]
      (partial (mapv m+ a b)))

(defn c- [a b]
      (partial (mapv m- a b)))

(defn c* [a b]
      (partial (mapv m* a b)))

(defn cd [a b]
      (partial (mapv md a b)))
