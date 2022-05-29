(ns reframe-basic-crud.tasks.subs
  (:require
   [re-frame.core :as re-frame]))


(re-frame/reg-sub
 ::all-tasks
 (fn [db _]
   (:tasks db)))