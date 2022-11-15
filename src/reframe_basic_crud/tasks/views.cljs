(ns reframe-basic-crud.tasks.views
  (:require
   [re-com.core :as re-com :refer [at]]
   [re-frame.core :as re-frame]
   [reagent.core :as reagent]
   [reframe-basic-crud.tasks.events :as events]
   [reframe-basic-crud.tasks.subs :as subs]
   [reframe-basic-crud.routes :as routes]))

;; tasks
(defn tasks-title []
  [re-com/title
   :src   (at)
   :label "Task manager"
   :level :level1])

(defn insert-task-pane []
  (let [input (reagent/atom nil)]
    (fn []
      [re-com/input-text
       :src (at)
       :model input
       :change-on-blur? true
       :on-change #(re-frame/dispatch [::events/insert-task %] )]))  )

(defn tasks-list []
  (let [all-tasks (re-frame/subscribe [::subs/all-tasks])]
    (fn []
      [re-com/simple-v-table
       :model all-tasks
       :columns [{:id :id
                  :header-label "ID"
                  :width 350
                  :row-label-fn :task/id}
                 {:id :description
                  :header-label "Description"
                  :width 650
                  :sort-by {}
                  :row-label-fn :task/description}
                 {:id :status
                  :header-label "Status"
                  :width 100
                  :sort-by {}
                  :row-label-fn :task/status}]])))

(defn tasks-panel []
  [re-com/v-box
   :src      (at)
   :gap      "1em"
   :children [[tasks-title]
              [insert-task-pane]
              [tasks-list]]])

(defmethod routes/panels :tasks-panel [] [tasks-panel])