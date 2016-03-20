(ns learning.core
  (:gen-class))

(defn rl []
  ; convenience function to reload current file
  (use 'learning.core :reload-all))

(defn mult-args
  ; function that takes at least two arguments, ignoring the others
  ; here c will be saved as a list (which could be any number of values)
  [a b & c]
  (println (str "A and B: " a ", " b))
  (println (str "Ignoring: " c)))

(defn go-to-location
  ; function that takes a map and returns a message about how to
  ; get there. dorun will show the side effects of a map over println
  ; which would normally just return nil for each call
  [{:keys [lat lng] :as location}]
  (println (str "HERE WE GO!"))
  (let [a (+ lat 10) b (+ lng 5)]
    (dorun (map println [(str "Values a: " a " b: " b " lat: " lat " lng: " lng)
                         (str "Distance to location: ")
                         (str "Latitude: " (- a lat))
                         (str "Longitude: " (- b lng))]))))

(defn location
  ; function that takes a map and destructures it and saves it to pass
  ; to another function
  [{:keys [lat lng] :as lct}]
    (dorun (map println [(str "Latitude: " lat)
                         (str "Longitude: " lng)]))
    (go-to-location lct))

(defn arity-example
  ; a function that shows arity overloading
  ([a b]
   (println (str "Arity of 2: " a " and " b)))
  ([a b c]
   (println (str "Arity of 3: " a " and " b " and " c)))
  ([{:keys [a b] :as a-and-b}]
   (println (str "Map Arity: " a " and " b))
   (arity-example (:a a-and-b) 12)
   (arity-example (:b a-and-b) 34)))

(defn anon-func
  ; demonstrate the use of anonymous functions in two differnent ways
  [a b]
  (println "Using fn syntax: " ((fn [x y] (+ x y 10)) a b))
  (println "Using #(%) syntax: " (#(+ %1 %2 10) a b)))

(defn loop-example
  ; function showing the use of loop and recur
  [howmany]
  (loop [increment 0]
    (println (str "Count: " increment "/" howmany))
    (if (>= increment howmany)
      (println "See ya!")
      (recur (inc increment)))))

(defn unique-vec-from-set
  ; creates a unique vector of the items listed (no repeating values)
  ; it also sorts them
  [& values]
  (sort (into [] (set values))))

(defn rand-samp-with-prob
  ; gets the random sample of 10 numbers (1-10)
  ; with p probability
  [p]
  (let [sample (repeatedly 10 #(rand-int 1000))]
    (loop [n 0]
      (if (>= n 10)
        (println "Done!")
        (do
          (println (random-sample p sample))
          (recur (inc n)))))))

(defn map-over-map
  ; map over a map and do something to each value
  ; (reduce (fn [m [k v]] (assoc m k (inc v))) {} {:some 1 :map 2})
  [func map]
  (into {} (for [[key value] map] [key (func value)])))

(defn my-count
  ; a version of count - comes from 4clojure
  ; #(loop [c 0 [h & t] %] (if (nil? t) (inc c) (recur (inc c) t)))
  [seq]
  (loop [count 0 [first & rest] seq]
    (if (nil? rest)
      (inc count)
      (recur (inc count) rest))))

(defn my-reverse
  ; a version of reverse - from 4clojure
  ; #(loop [n '() [h & t] %] (if (nil? t) (cons h n) (recur (cons h n) t)))
  [s]
  (loop [n '() [h & t] s]
    (if (nil? t)
      (cons n h)
      (recur (cons n h) t))))


; left off at http://www.braveclojure.com/core-functions-in-depth/ on filter and some (a little less than half way down!)
