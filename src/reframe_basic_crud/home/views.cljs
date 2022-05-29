(ns reframe-basic-crud.home.views
  (:require
   [re-com.core :as re-com :refer [at]]
   [reframe-basic-crud.routes :as routes]))

;; home
(defn home-title []
  [re-com/title
   :src   (at)
   :label (str "This is the Home Page.")
   :level :level1])

(defn home-panel []
  [re-com/v-box
   :src      (at)
   :gap      "1em"
   :children [[home-title]]])

(defmethod routes/panels :home-panel [] [home-panel])