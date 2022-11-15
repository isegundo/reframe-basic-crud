(ns reframe-basic-crud.subs
  (:require
   [re-frame.core :as re-frame]))

(re-frame/reg-sub
 ::app-name
 (fn [db]
   (:app-name db)))

(re-frame/reg-sub
 ::active-panel
 (fn [db _]
   (:active-panel db)))
