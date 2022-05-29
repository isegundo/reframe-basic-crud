(ns reframe-basic-crud.tasks.events
  (:require
   [re-frame.core :as re-frame]
   [reframe-basic-crud.tasks.logic :as logic]
   [day8.re-frame.tracing :refer-macros [fn-traced]]))


(re-frame/reg-event-db
 ::insert-task
 (fn-traced [db [_ description]]
            (update-in db [:tasks] conj (logic/new-task description))))
