import { getCurrentStartEnd, getCurrentTime } from '../module/moment';
import { Alert } from '../module/alert';

const jqueryCall = () => {
    $('.jquery').text('jQuery Use');
};

const momentCall = () => {
    $('.moment').text(getCurrentTime());
    console.log('moment', getCurrentStartEnd());
};

const lodashCall = () => {
    const myFriend = [
        { name: 'kys', job: 'developer', age: 27 },
        { name: 'cys', job: 'webtoons man', age: 27 },
        { name: 'yhs', job: 'florist', age: 26 },
        { name: 'chj', job: 'nonghyup man', age: 27 },
        { name: 'ghh', job: 'coffee man', age: 27 },
        { name: 'ldh', job: 'kangaroo father', age: 27 },
        { name: 'hsy', job: 'monk', age: 27 },
    ];
    let val;
    // eslint-disable-next-line prefer-const
    val = _.findIndex(myFriend, (friend) => friend.age === 26);
    return val;
};

const alertCall = () => {
    Alert();
};

// eslint-disable-next-line no-undef
$(document).ready(() => {
    jqueryCall();
    momentCall();
    console.log('lodash', lodashCall());
    alertCall();
});
