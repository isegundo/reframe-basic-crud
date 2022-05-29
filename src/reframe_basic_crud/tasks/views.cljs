(ns reframe-basic-crud.tasks.views
  (:require
   [re-com.core :as re-com :refer [at]]
   [re-frame.core :as re-frame]
   [reframe-basic-crud.tasks.subs :as subs]
   [reframe-basic-crud.routes :as routes]))

;; tasks
(defn tasks-title []
  [re-com/title
   :src   (at)
   :label "Task manager"
   :level :level1])

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
                  :width 700
                  :row-label-fn :task/description}
                 {:id :status
                  :header-label "Status"
                  :width 70
                  :row-label-fn :task/status}]])))

(defn tasks-panel []
  [re-com/v-box
   :src      (at)
   :gap      "1em"
   :children [[tasks-title]
              ;; [insert-task-button]
              [tasks-list]]])

(defmethod routes/panels :tasks-panel [] [tasks-panel])