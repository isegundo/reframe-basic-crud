(ns reframe-basic-crud.tasks.views
  (:require
   [re-com.core :as re-com :refer [at]]
   [reframe-basic-crud.routes :as routes]))

;; tasks
(defn tasks-title []
  [re-com/title
   :src   (at)
   :label "This is the Tasks Page."
   :level :level1])

(defn tasks-panel []
  [re-com/v-box
   :src      (at)
   :gap      "1em"
   :children [[tasks-title]]])

(defmethod routes/panels :tasks-panel [] [tasks-panel])