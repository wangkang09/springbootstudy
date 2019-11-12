
| Method                                   | Summary                                                      | Detail                                                       |
| ---------------------------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| start()                                  | 开启调度线程执行Triggers                                     | 当初始化schedule时不会立即执行triggers，只有执行start()方法时才正式执行 |
| startDelayed(int)                        | 延迟执行                                                     | 等待应用资源初始化完毕后在执行                               |
| shutdown()                               | 暂停调度程序触发触发器，并清除与调度程序关联的所有资源       | scheduler不能重启                                            |
| standby()                                | 暂时停止调度程序触发触发器                                   | 初始化schedule时，schedule就是stand-by模式，scheduler可以重启 |
| shutdown(true)                           | 同shutdown()                                                 | 会等到所有正在执行的jobs完成在关闭                           |
| getCurrentlyExectionJobs                 | 获取当前运行的Jobs                                           |                                                              |
| getListenerManager                       | 获取监听管理器                                               | 获取对调度程序的ListenerManager的引用，通过该ListenerManager可以注册侦听器 |
| scheduleJob(job,trigger)                 | add job and associate trigger                                | 如果Trigger没有关联任何Job且此job不存在则成功                |
| scheduleJob(trigger)                     | 设置trigger给某个job                                         | 如果job不存在报错                                            |
| scheduleJobs(job,set\<trigger>,replace)  | 设置triggers给某个job                                        | If any of the given jobs or triggers already exist (or more specifically, if the keys are not unique) and the replace parameter is not set to true then an exception will be thrown. |
| scheduleJobs(map\<job,set\<trigger>>)    | 同上                                                         |                                                              |
| unscheduleJob(triggerKey)                | Remove the indicated `Trigger` from the scheduler.           | If the related job does not have any other triggers, and the job is not durable, then the job will also be deleted |
| unscheduleJobs(triggerkeys)              | 同上                                                         |                                                              |
| rescheduleJob(triggerkey,newTrigger)     | 删除给定triggerket的trigger,newTrigger对应的job一定要和旧trigger一样 | 如果triggerkey没找到，返回null。否则返回新trigger第一次fire time |
| addJob(job,replace)                      | 添加job，不关联trigger                                       | 只有当有trigger与之关联时才不休眠                            |
| deleteJob(jobkey)                        |                                                              | Delete the identified `Job` from the Scheduler - and any associated `Trigger`s. |
| deleteJobs(jobkeys)                      | 同上                                                         |                                                              |
| triggerJob(jobKey)                       | 执行一次jobKey对应的JobDetail                                |                                                              |
| triggerJob(jobKey,jobDataMap)            | 同上                                                         | 额外加了datamap信息                                          |
| pauseJob(jobkey)                         |                                                              | 暂停jobkey对应的Triggers                                     |
| pauseJobs(GroupMatcher\<jobkey>)         |                                                              | 暂停所有匹配的Triggers                                       |
| pauseTrigger(triggerKey)                 | 暂停对应的trigger                                            |                                                              |
| pauseTriggers(GroupMatcher\<TriggerKey>) |                                                              | 暂停所有匹配的Triggers                                       |
| resumexxx                                |                                                              | 对应pausexxx                                                 |
| pauseAll()                               |                                                              |                                                              |
| clear()                                  |                                                              | Clears (deletes!) all scheduling data - all Jobs, Triggers Calendars |

