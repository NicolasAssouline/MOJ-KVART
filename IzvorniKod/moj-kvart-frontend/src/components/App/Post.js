import React, {Component} from 'react';
import FormDialogChangePost from './FormDialogChangePost';
import dateFormat from 'dateformat';
import './Post.css';
import FormDialogDeletePost from './FormDialogDeletePost';

class Post extends Component {

    render(){
        const {id, content, postedOn, postedBy} = this.props.post;

        var changePost;
        let deletePost;

        if(this.props.role === 'MODERATOR'){
            changePost = <FormDialogChangePost
                            post={this.props.post}
                            componentDidMount={this.props.componentDidMount}
                />
            deletePost = <FormDialogDeletePost
                            post={this.props.post}
                            componentDidMount={this.props.componentDidMount}
                />
        }

        if (this.props.userId === postedBy.id) {
           changePost = <FormDialogChangePost 
                   post={this.props.post}
                   componentDidMount={this.props.componentDidMount}
           />
           deletePost = <FormDialogDeletePost
                           post={this.props.post}
                           componentDidMount={this.props.componentDidMount}
           />
        } 
        


        return(
            <div>
            <div className='post_card'>
                <div className='posted_on_card'> 
                    {dateFormat(postedOn, 'dd/mm/yy')} 
                    {deletePost}
                    {changePost}
                </div>
                <div>
                    {content}
                </div>
                <div className='posted_by_card'>
                   {postedBy.username}
                </div>
             </div>
         </div>
        );


    }
}

export default Post;

