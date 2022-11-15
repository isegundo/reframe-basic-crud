(ns reframe-basic-crud.home.views
  (:require
   [re-com.core :as re-com :refer [at]]
   [reagent.core :as reagent]
   [reframe-basic-crud.routes :as routes]))

;; home
(defn home-title []
  [re-com/title
   :src   (at)
   :label (str "This is the Home Page.")
   :level :level1])

(defn counter [counter-name initial-value] ; TODO a schema validation?
  (let [counter-state (reagent/atom initial-value)]
    (fn [] ; Notice this a form-2 component, but not sure if it is correct
      [re-com/v-box
       :children [[re-com/h-box
                   :children [[:h3 counter-name ": " @counter-state]]]
                  [re-com/h-box
                   :children [[re-com/button
                               :label "-"
                               :on-click #(swap! counter-state dec)] ; one way to write anonymous function
                              [re-com/button
                               :label "+"
                               :on-click (fn [] (swap! counter-state inc))]]]]])))

(defn blue-line []
  [re-com/line
   :size "3px"
   :color "blue"])

(defn mouse-pos []
  (reagent/with-let [pointer (reagent/atom nil)
                     handler #(swap! pointer assoc
                                     :x (.-pageX %)
                                     :y (.-pageY %))
                     _ (.addEventListener js/document "mousemove" handler)]
    @pointer
    (finally
      (.removeEventListener js/document "mousemove" handler))))

(defn tracked-pos []
  [:div
   "Pointer moved to: "
   (str @(reagent/track mouse-pos))])

(defn home-panel []
  [re-com/v-box
   :src      (at)
   :gap      "1em"
   :children [[home-title]
              [counter "Counter 1" 0]
              [blue-line]
              [counter "Counter 2" 10]
              [blue-line]
              [tracked-pos]]])

(defmethod routes/panels :home-panel [] [home-panel])